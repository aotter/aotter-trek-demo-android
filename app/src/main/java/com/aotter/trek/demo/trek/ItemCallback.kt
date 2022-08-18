package com.aotter.trek.demo.trek

import androidx.recyclerview.widget.DiffUtil
import com.aotter.trek.demo.trek.LocalNativeAdData

class ItemCallback : DiffUtil.ItemCallback<LocalNativeAdData>() {

    override fun areItemsTheSame(oldItem: LocalNativeAdData, newItem: LocalNativeAdData): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(
        oldItem: LocalNativeAdData,
        newItem: LocalNativeAdData
    ): Boolean {
        return oldItem.postId == newItem.postId
    }

}