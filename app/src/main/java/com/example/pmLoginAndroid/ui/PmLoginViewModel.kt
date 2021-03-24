package com.example.pmLoginAndroid.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmLoginAndroid.data.LoginError
import com.example.pmLoginAndroid.data.LoginSocial
import com.example.pmLoginAndroid.data.UriBuilderFactory
import com.example.pmLoginAndroid.data.api.PmService
import com.example.pmLoginAndroid.data.mapper.AvailableSocialsMapper
import com.example.pmLoginAndroid.data.request.ChosenSocialRequestData
import com.example.pmLoginAndroid.data.request.ProfileRequestData
import com.example.pmLoginAndroid.data.response.ProfileData
import com.example.pmLoginAndroid.utils.ResultWrapper
import com.example.pmLoginAndroid.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PmLoginViewModel @Inject constructor(
    private val urlBuilderFactory: UriBuilderFactory,
    private val pmService: PmService,
    private val socialsMapper: AvailableSocialsMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>(ViewState.Loading)
    val viewState: LiveData<ViewState> = _viewState

    private lateinit var currentSessionId: String

    fun loadAvailableSocials() {
        viewModelScope.launch(Dispatchers.IO) {
            val socials = safeApiCall(pmService::getAvailableSocials)
            withContext(Dispatchers.Main) {
                _viewState.value = when (socials) {
                    is ResultWrapper.Success ->
                        ViewState.SocialSelect(socialsMapper.map(socials.value))
                    is ResultWrapper.GenericError ->
                        ViewState.Error(LoginError.GenericError)
                    is ResultWrapper.NetworkError ->
                        ViewState.Error(LoginError.NetworkError)
                }
            }
        }
    }

    fun loadAuthUriData(social: LoginSocial, redirectUrl: String) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val data =
                safeApiCall {
                    pmService.getAuthUriData(
                        ChosenSocialRequestData(
                            social.id,
                            redirectUrl
                        )
                    )
                }
            withContext(Dispatchers.Main) {
                _viewState.value = when (data) {
                    is ResultWrapper.Success -> {
                        val urlBuilder = urlBuilderFactory.create(social)
                        val uri = urlBuilder.build(data.value)
                        currentSessionId = data.value.state
                        ViewState.BrowserLogin(uri)
                    }
                    is ResultWrapper.GenericError ->
                        ViewState.Error(LoginError.GenericError)
                    is ResultWrapper.NetworkError ->
                        ViewState.Error(LoginError.NetworkError)
                }
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
            withContext(Dispatchers.Main) {
                _viewState.value = when (profile) {
                    is ResultWrapper.Success -> {
                        ViewState.Success(profile.value)
                    }
                    is ResultWrapper.GenericError -> {
                        Log.d("wtf", profile.code.toString())
                        Log.d("wtf", profile.error.toString())
                        ViewState.Error(LoginError.GenericError)
                    }
                    is ResultWrapper.NetworkError ->
                        ViewState.Error(LoginError.NetworkError)
                }
            }
        }
    }
}


internal sealed class ViewState {
    object Loading : ViewState()
    data class SocialSelect(val data: List<LoginSocial>) : ViewState()
    data class BrowserLogin(val uri: Uri) : ViewState()
    data class Error(val error: LoginError) : ViewState()
    data class Success(val profile: ProfileData) : ViewState()
}