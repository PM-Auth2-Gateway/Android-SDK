package com.example.pmLoginAndroid.di

import com.example.pmLoginAndroid.ui.PmLoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibraryModule::class, ViewModelModule::class])
internal interface LibraryComponent {
    fun inject(fragment: PmLoginFragment)
}
