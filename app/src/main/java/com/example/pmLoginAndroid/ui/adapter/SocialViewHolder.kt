package com.example.pmLoginAndroid.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pmLoginAndroid.R
import com.example.pmLoginAndroid.data.response.AvailableSocialModel
import com.example.pmLoginAndroid.databinding.SocialItemBinding

internal class SocialViewHolder(itemView: View, private val callback: (Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = SocialItemBinding.bind(itemView)

    fun bind(social: AvailableSocialModel) {
        binding.apply {
            tvSocialName.text = social.name
            root.setOnClickListener {
                callback(social.id)
            }
            Glide.with(ivSocialIcon)
                .load(social.logoPath)
                .centerCrop()
                .placeholder(R.drawable.icon_placeholder)
                .into(ivSocialIcon)
        }
    }
}
