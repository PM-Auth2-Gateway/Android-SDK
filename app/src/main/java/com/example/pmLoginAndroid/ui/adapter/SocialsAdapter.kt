package com.example.pmLoginAndroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pmLoginAndroid.R
import com.example.pmLoginAndroid.data.response.LoginSocial

internal class SocialsAdapter(private val callback: (LoginSocial) -> Unit) :
    ListAdapter<LoginSocial, SocialViewHolder>(SocialsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.social_item, parent, false)
        return SocialViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

