package com.aotter.trek.android.kotlin.demo.trek.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.Entity
import com.aotter.net.dto.Location
import com.aotter.net.dto.User
import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.AotterTrek
import com.aotter.net.trek.ads.TrekAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.net.trek.annomation.EntityType
import com.aotter.net.trek.sealed.ActionType
import com.aotter.trek.demo.databinding.ActivityNativeAdRecyclerviewPageBinding
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.LocalNativeAdData
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.NativeAdAdapter
import com.google.gson.JsonObject

class TrekNativeAdRecyclerViewPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNativeAdRecyclerviewPageBinding

    private lateinit var trekAd: TrekAd

    private lateinit var trekAd2: TrekAd

    private val trekAdRequest = TrekAdRequest.Builder()
        .setCategory("news")
        .setContentUrl("https://agirls.aotter.net/")
        .setContentTitle("電獺少女")
        .build()

    private lateinit var nativeAdAdapter: NativeAdAdapter

    private var list: MutableList<LocalNativeAdData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdRecyclerviewPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        initTracker()

        trekAd = AotterTrek.trekService(this)

        trekAd2 = AotterTrek.trekService(this)

        getAd()

    }


    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {
            trekAd.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").loadAd(trekAdRequest)
        }


        nativeAdAdapter = NativeAdAdapter()

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        viewBinding.nativeAdRecyclerView.layoutManager = linearLayoutManager

        viewBinding.nativeAdRecyclerView.adapter = nativeAdAdapter

        repeat(10) {
            list.add(
                LocalNativeAdData(
                    "幸運調色盤：12星座明天穿什麼？（6/6-6/12）",
                    "電獺少女",
                    "http://pnn.aotter.net/Media/show/d8404d54-aab7-4729-8e85-64fb6b92a84e.jpg"
                )
            )
        }

        nativeAdAdapter.update(list)


    }

    private fun getAd() {

        trekAd.setTrekAdListener(object : TrekAdListener {
            override fun onAdFailedToLoad(message: String) {
                Log.e("onAdError", message)

                getAd2()
            }

            override fun onAdLoaded(adData: AdData) {

                list[1].trekAd?.let {
                    list[1] = LocalNativeAdData(
                        adData.title,
                        adData.advertiserName,
                        adData.imgIcon.uri.toString(),
                        trekAd,
                        adData
                    )
                } ?: kotlin.run {
                    list.add(
                        1,
                        LocalNativeAdData(
                            adData.title,
                            adData.advertiserName,
                            adData.imgIcon.uri.toString(),
                            trekAd,
                            adData
                        )
                    )
                }

                getAd2()

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked() {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression() {
                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").loadAd(trekAdRequest)

    }

    private fun getAd2() {

        trekAd2.setTrekAdListener(object : TrekAdListener {
            override fun onAdFailedToLoad(message: String) {
                Log.e("onAdError", message)
                nativeAdAdapter.update(list)
            }

            override fun onAdLoaded(adData: AdData) {

                list[8].trekAd?.let {
                    list[8] = LocalNativeAdData(
                        adData.title,
                        adData.advertiserName,
                        adData.imgIcon.uri.toString(),
                        trekAd2,
                        adData
                    )
                } ?: kotlin.run {
                    list.add(
                        8,
                        LocalNativeAdData(
                            adData.title,
                            adData.advertiserName,
                            adData.imgIcon.uri.toString(),
                            trekAd2,
                            adData
                        )
                    )
                }

                nativeAdAdapter.update(list)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked() {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression() {
                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd2.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").loadAd(trekAdRequest)

    }

    private fun initTracker() {

        val tracker = AotterTrek.trackerService(this)

        val entity = Entity(
            "anthony", "anthony", EntityType.PLACE, "anthony", listOf("炸雞"),
            listOf(
                "News",
                "News_domestic"
            ),
            JsonObject(),
        )

        val user = User()

        tracker
            .timeSpan(1)
            .setEntity(entity)
            .setUser(user)
            .setLocation(Location())
            .setActionType(ActionType.READ_POST)
            .sendTrackerReport()

    }


}