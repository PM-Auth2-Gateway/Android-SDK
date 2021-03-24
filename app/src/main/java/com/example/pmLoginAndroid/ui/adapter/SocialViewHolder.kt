package com.example.pmLoginAndroid.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmLoginAndroid.data.LoginSocial
import com.example.pmLoginAndroid.databinding.SocialItemBinding

internal class SocialViewHolder(itemView: View, private val callback: (LoginSocial) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = SocialItemBinding.bind(itemView)

    fun bind(social: LoginSocial) {
        binding.apply {
            root.text = social.label
            root.setCompoundDrawablesRelativeWithIntrinsicBounds(social.icon, 0, 0, 0)
            root.setOnClickListener {
                callback(social)
            }
        }
    }
}
