package com.example.pmLoginAndroid.client

import androidx.appcompat.app.AppCompatActivity
import com.example.pmLoginAndroid.ui.PmLoginFragment

object PmLogin {

    class PmClient(options: PmOptions) {
        internal companion object {
            lateinit var component: LibraryComponent
        }

        init {
            component = DaggerLibraryComponent.builder()
                .libraryModule(LibraryModule(options))
                .build()
        }

        fun startLogin(activity: AppCompatActivity) {
            PmLoginFragment.newInstance().show(activity.supportFragmentManager, null)
        }
    }

    data class PmOptions(
        val appId: String,
        val redirectUrl: String,
        val onSuccessCallback: (String) -> Unit,
        val onErrorCallback: () -> Unit
    )
}