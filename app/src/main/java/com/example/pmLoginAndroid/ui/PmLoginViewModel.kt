package com.example.pmLoginAndroid.ui

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.client.model.LoginResult
import com.example.pmLoginAndroid.data.api.PmService
import com.example.pmLoginAndroid.data.mapper.AvailableSocialsMapper
import com.example.pmLoginAndroid.data.mapper.ErrorMapper
import com.example.pmLoginAndroid.data.request.ChosenSocialRequestData
import com.example.pmLoginAndroid.data.request.ProfileRequestData
import com.example.pmLoginAndroid.data.response.LoginSocial
import com.example.pmLoginAndroid.data.uris.UriBuilderFactory
import com.example.pmLoginAndroid.data.usecases.RequiredFieldUseCase
import com.example.pmLoginAndroid.utils.ResultWrapper
import com.example.pmLoginAndroid.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO Reduce params amount
// Maybe should extract result mapping to repository?
@Suppress("LongParameterList")
internal class PmLoginViewModel @Inject constructor(
    private val urlBuilderFactory: UriBuilderFactory,
    private val pmService: PmService,
    private val socialsMapper: AvailableSocialsMapper,
    private val pmOptions: PmLogin.PmOptions,
    private val loginResultObservable: MutableLiveData<LoginResult>,
    private val requiredFieldUseCase: RequiredFieldUseCase,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>(ViewState.Loading)
    val viewState: LiveData<ViewState> = _viewState

    private lateinit var currentSessionId: String

    fun loadAvailableSocials() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val socials = safeApiCall(pmService::getAvailableSocials)) {
                is ResultWrapper.Success -> {
                    withContext(Dispatchers.Main) {
                        _viewState.value =
                            ViewState.SocialSelect(socialsMapper.map(socials.value))
                    }
                }
                is ResultWrapper.Error -> onNetworkRequestError(socials)
            }
        }
    }

    fun loadAuthUriData(social: LoginSocial) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val chosenSocialRequestData = ChosenSocialRequestData(social.id, pmOptions.redirectUrl)

            when (val uriData = safeApiCall { pmService.getAuthUriData(chosenSocialRequestData) }) {
                is ResultWrapper.Success -> {
                    val urlBuilder = urlBuilderFactory.create(social)
                    val uri = urlBuilder.build(uriData.value)
                    withContext(Dispatchers.Main) {
                        currentSessionId = uriData.value.state
                        _viewState.value = ViewState.BrowserLogin(uri)
                    }
                }
                is ResultWrapper.Error -> onNetworkRequestError(uriData)
            }
        }
    }

    fun onBrowserLoginFailed() {
        _viewState.value = ViewState.Error(LoginError.GenericError)
    }

    fun requestProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile =
                safeApiCall { pmService.getProfileInfo(ProfileRequestData(currentSessionId)) }

            when (profile) {
                is ResultWrapper.Success -> {
                    withContext(Dispatchers.Main) {
                        loginResultObservable.value = requiredFieldUseCase.invoke(profile.value)
                        _viewState.value =
                            if (loginResultObservable.value is LoginResult.Success) ViewState.Success
                            else ViewState.Error(LoginError.NoRequiredFieldsError)
                    }
                }
                is ResultWrapper.Error -> onNetworkRequestError(profile)
            }
        }
    }

    private suspend fun onNetworkRequestError(request: ResultWrapper.Error) {
        val error = errorMapper.map(request)
        withContext(Dispatchers.Main) {
            _viewState.value = ViewState.Error(error)
            loginResultObservable.value = LoginResult.Error(error)
        }
    }
}


internal sealed class ViewState {
    object Loading : ViewState()
    data class SocialSelect(val data: List<LoginSocial>) : ViewState()
    data class BrowserLogin(val uri: Uri) : ViewState()
    data class Error(val error: LoginError) : ViewState()
    object Success : ViewState()
}
