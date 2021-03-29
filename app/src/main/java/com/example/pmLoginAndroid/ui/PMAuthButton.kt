package com.example.pmLoginAndroid.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.pmLoginAndroid.R

class PMAuthButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) :
    LinearLayout(context, attributeSet) {

    init {
        inflate(context, R.layout.pm_login_btn, this)
    }
}
