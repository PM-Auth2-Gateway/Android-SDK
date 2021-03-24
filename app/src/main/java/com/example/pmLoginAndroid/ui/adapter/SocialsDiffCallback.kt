package com.example.pmLoginAndroid.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pmLoginAndroid.data.LoginSocial

internal class SocialsDiffCallback : DiffUtil.ItemCallback<LoginSocial>() {
    override fun areItemsTheSame(oldItem: LoginSocial, newItem: LoginSocial): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: LoginSocial, newItem: LoginSocial): Boolean =
        oldItem == newItem
}
