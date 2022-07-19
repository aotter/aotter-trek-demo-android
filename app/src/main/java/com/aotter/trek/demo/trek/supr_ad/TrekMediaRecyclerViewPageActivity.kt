package com.aotter.trek.demo.trek.supr_ad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdLoader
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.net.trek.ads.ad_view_binding.TrekAdViewBinder
import com.aotter.trek.demo.databinding.ActivityMediaRecyclerViewPageBinding
import com.aotter.trek.demo.trek.CoverPageActivity
import com.aotter.trek.demo.trek.LocalSuprAdData
import com.aotter.trek.demo.trek.SuprAdAdapter

class TrekMediaRecyclerViewPageActivity : AppCompatActivity() {

    private val TAG = TrekMediaRecyclerViewPageActivity::class.java.simpleName

    private lateinit var viewBinding: ActivityMediaRecyclerViewPageBinding

    private lateinit var trekAdLoader: TrekAdLoader

    private lateinit var trekAdLoader2: TrekAdLoader

    private val trekAdRequest = TrekAdRequest.Builder()
        .setCategory("news")
        .setContentUrl("https://agirls.aotter.net/")
        .setContentTitle("電獺少女")
        .build()

    private lateinit var suprAdAdapter: SuprAdAdapter

    private var list: MutableList<LocalSuprAdData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMediaRecyclerViewPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        getAd()

    }


    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            TrekAdViewBinder.destroyAll()

            trekAdLoader.loadAd(trekAdRequest)

        }

        viewBinding.coverpageBtn.setOnClickListener {

            val intent = Intent()
            intent.setClass(this, CoverPageActivity::class.java)
            startActivity(intent)

        }

        suprAdAdapter = SuprAdAdapter()

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        viewBinding.suprAdRecycletView.layoutManager = linearLayoutManager

        viewBinding.suprAdRecycletView.adapter = suprAdAdapter

        repeat(10) {
            list.add(
                LocalSuprAdData(
                    "幸運調色盤：12星座明天穿什麼？（6/6-6/12）",
                    "http://pnn.aotter.net/Media/show/d8404d54-aab7-4729-8e85-64fb6b92a84e.jpg"
                )
            )
        }

        suprAdAdapter.update(list)

    }

    private fun getAd() {

        trekAdLoader =
            TrekAdLoader.Builder(this, "81608f91-8b2b-4f8f-86a1-539a1959f836")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        list[1].trekNativeAd?.let {
                            list[1] = LocalSuprAdData(
                                trekNativeAd.title,
                                trekNativeAd.advertiserName,
                                trekNativeAd
                            )
                        } ?: kotlin.run {
                            list.add(
                                1,
                                LocalSuprAdData(
                                    trekNativeAd.title,
                                    trekNativeAd.advertiserName,
                                    trekNativeAd
                                )
                            )
                        }

                        getAd2()


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

                        list[8].trekNativeAd?.let {
                            list[8] = LocalSuprAdData(
                                trekNativeAd.title,
                                trekNativeAd.advertiserName,
                                trekNativeAd
                            )
                        } ?: kotlin.run {
                            list.add(
                                8,
                                LocalSuprAdData(
                                    trekNativeAd.title,
                                    trekNativeAd.advertiserName,
                                    trekNativeAd
                                )
                            )
                        }

                        suprAdAdapter.update(list)


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