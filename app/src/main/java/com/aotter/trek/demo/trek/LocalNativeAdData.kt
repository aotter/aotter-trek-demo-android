package com.aotter.trek.demo.trek

import com.aotter.net.dto.trek.response.TrekNativeAd

data class LocalNativeAdData(
    val title: String,
    val advertiser: String,
    val img: String,
    val trekNativeAd: TrekNativeAd? = null
)