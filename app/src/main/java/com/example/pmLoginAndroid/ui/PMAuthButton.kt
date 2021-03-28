package com.example.pmLoginAndroid.ui

import android.content.Context
import android.widget.LinearLayout
import com.example.pmLoginAndroid.R

class PMAuthButton (context: Context) : LinearLayout(context) {
    init {
        inflate(context, R.layout.pm_login_btn, this)
    }
}