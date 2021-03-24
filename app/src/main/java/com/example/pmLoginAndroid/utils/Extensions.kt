package com.example.pmLoginAndroid.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal fun View.setVisible(vsbl: Boolean) {
    visibility = if (vsbl) View.VISIBLE else View.GONE
}

internal inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}