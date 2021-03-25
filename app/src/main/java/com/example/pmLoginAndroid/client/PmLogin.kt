package com.example.pmLoginAndroid.client

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pmLoginAndroid.client.model.LoginResult
import com.example.pmLoginAndroid.di.DaggerLibraryComponent
import com.example.pmLoginAndroid.di.LibraryComponent
import com.example.pmLoginAndroid.di.LibraryModule
import com.example.pmLoginAndroid.ui.PmLoginFragment

object PmLogin {

    class PmClient(options: PmOptions) {
        internal companion object {
            lateinit var component: LibraryComponent
        }

        private val _loginResult = MutableLiveData<LoginResult>()
        val loginResult: LiveData<LoginResult> = _loginResult

        init {
            component = DaggerLibraryComponent.builder()
                .libraryModule(LibraryModule(options, _loginResult))
                .build()
        }

        fun startLogin(activity: AppCompatActivity) {
            PmLoginFragment.newInstance().show(activity.supportFragmentManager, null)
        }
    }

    data class PmOptions(
        val appId: String,
        val redirectUrl: String,
        val requiredFields: List<String>
    )
}
