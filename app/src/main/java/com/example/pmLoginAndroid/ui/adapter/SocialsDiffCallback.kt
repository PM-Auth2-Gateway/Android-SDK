package com.example.pmLoginAndroid.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pmLoginAndroid.data.response.AvailableSocialModel

internal class SocialsDiffCallback : DiffUtil.ItemCallback<AvailableSocialModel>() {
    override fun areItemsTheSame(
        oldItem: AvailableSocialModel,
        newItem: AvailableSocialModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AvailableSocialModel,
        newItem: AvailableSocialModel
    ): Boolean {
        return oldItem == newItem
    }
}
