package com.aotter.trek.android.kotlin.demo.trek.demo.trek

import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.ads.TrekAd

data class LocalSuprAdData(
    val title: String,
    val img: String,
    val trekAd: TrekAd? = null,
    val adData: AdData? = null
)