package com.example.pmLoginAndroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pmLoginAndroid.ui.PmLoginViewModel
import com.example.pmLoginAndroid.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PmLoginViewModel::class)
    internal abstract fun pmLoginViewModel(viewModel: PmLoginViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
