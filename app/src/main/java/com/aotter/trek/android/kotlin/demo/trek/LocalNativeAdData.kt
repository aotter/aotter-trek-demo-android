package com.aotter.trek.demo.trek

import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.ads.TrekAd

data class LocalNativeAdData(
    val title: String,
    val advertiser: String,
    val img: String,
    val trekAd: TrekAd? = null,
    val adData: AdData? = null
)