package com.aotter.trek.demo.trek.supr_ad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdLoader
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.net.trek.ads.ad_view_binding.TrekAdViewBinder
import com.aotter.trek.demo.databinding.ActivityMediaScrollPageBinding
import com.aotter.trek.demo.trek.CoverPageActivity

class TrekMediaScrollPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMediaScrollPageBinding

    private val TAG = TrekMediaScrollPageActivity::class.java.simpleName

    private lateinit var trekAdLoader: TrekAdLoader

    private lateinit var trekAdLoader2: TrekAdLoader

    private val trekAdRequest = TrekAdRequest.Builder()
        .setCategory("news")
        .setContentUrl("https://agirls.aotter.net/")
        .setContentTitle("電獺少女")
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMediaScrollPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        getAd()

        getAd2()

    }

    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            TrekAdViewBinder.destroyAll()

            trekAdLoader.loadAd(trekAdRequest)

            trekAdLoader2.loadAd(trekAdRequest)

        }

        viewBinding.coverpageBtn.setOnClickListener {

            val intent = Intent()
            intent.setClass(this, CoverPageActivity::class.java)
            startActivity(intent)

        }

    }

    private fun getAd() {

        trekAdLoader =
            TrekAdLoader.Builder(this, "81608f91-8b2b-4f8f-86a1-539a1959f836")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        TrekAdViewBinder.registerAdView(
                            viewBinding.container,
                            viewBinding.trekMediaView4,
                            trekNativeAd
                        )

                    }

                    override fun onAdClicked() {
                        Log.e(TAG, "trekAdLoader : Ad Clicked success.")
                    }

                    override fun onAdImpression() {

                        Log.e(TAG, "trekAdLoader : AD Impression success.")

                    }

                })
                .build()

        trekAdLoader.loadAd(trekAdRequest)

    }

    private fun getAd2() {

        trekAdLoader2 =
            TrekAdLoader.Builder(this, "81608f91-8b2b-4f8f-86a1-539a1959f836")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader2 : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        TrekAdViewBinder.registerAdView(
                            viewBinding.container2,
                            viewBinding.trekMediaView10,
                            trekNativeAd
                        )

                    }

                    override fun onAdClicked() {
                        Log.e(TAG, "trekAdLoader2 : Ad Clicked success.")
                    }

                    override fun onAdImpression() {

                        Log.e(TAG, "trekAdLoader2 : AD Impression success.")

                    }

                })
                .build()

        trekAdLoader2.loadAd(trekAdRequest)

    }

    override fun onDestroy() {
        super.onDestroy()

        TrekAdViewBinder.destroyAll()

    }


}