package com.example.pmLoginAndroid.utils

import android.os.SystemClock
import android.view.View
import com.example.pmLoginAndroid.utils.LastTimeClicked.lastClickTime

fun View.onSingleClickListener(defaultInterval: Int = 300, onSafeClick: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {

        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < defaultInterval) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            onSafeClick(view)
        }
    })
}

internal object LastTimeClicked {
    var lastClickTime: Long = 0
}
