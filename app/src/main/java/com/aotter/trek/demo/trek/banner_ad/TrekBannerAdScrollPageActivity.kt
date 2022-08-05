package com.aotter.trek.demo.trek.banner_ad

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.trek.demo.databinding.ActivityBannerAdScrollViewBinding

class TrekBannerAdScrollPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityBannerAdScrollViewBinding

    private var trekAdRequest: TrekAdRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityBannerAdScrollViewBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        getBannerAd()

    }

    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            trekAdRequest?.let {
                viewBinding.trekBannerAdView.loadAd(it)
            }

        }


    }

    private fun getBannerAd() {

        viewBinding.trekBannerAdView.setPlaceUid("68856f90-83b7-4f09-98d4-7f480842cb02")

        viewBinding.trekBannerAdView.setAdListener(object : TrekAdListener {
            override fun onAdFailedToLoad(message: String) {
                super.onAdFailedToLoad(message)

                Log.e("onAdFailedToLoad", "onAdFailedToLoad")

            }

            override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                super.onAdLoaded(trekNativeAd)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked() {
                super.onAdClicked()

                Log.e("onAdClicked", "onAdClicked")

            }

            override fun onAdImpression() {
                super.onAdImpression()

                Log.e("onAdImpression", "onAdImpression")


            }
        })

        trekAdRequest = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdRequest?.let {
            viewBinding.trekBannerAdView.loadAd(it)
        }


    }

}