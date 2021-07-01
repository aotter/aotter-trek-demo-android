package com.aotter.trek.android.kotlin.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.Entity
import com.aotter.net.dto.Location
import com.aotter.net.dto.User
import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.AotterTrek
import com.aotter.net.trek.ads.TrekAd
import com.aotter.net.trek.ads.TrekAdStatusCallBack
import com.aotter.net.trek.annomation.EntityType
import com.aotter.net.trek.sealed.ActionType
import com.aotter.trek.android.kotlin.demo.LocalNativeAdData
import com.aotter.trek.android.kotlin.demo.trek.NativeAdAdapter
import com.aotter.trek.demo.databinding.ActivityNativeAdRecyclerviewPageBinding
import com.google.gson.JsonObject

class TrekNativeAdRecyclerViewPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNativeAdRecyclerviewPageBinding

    private lateinit var trekAd: TrekAd

    private lateinit var trekAd2: TrekAd


    private lateinit var nativeAdAdapter: NativeAdAdapter

    private var list: MutableList<LocalNativeAdData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdRecyclerviewPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        AotterTrek.initAotterService(
            this@TrekNativeAdRecyclerViewPageActivity,
            "DNgNhOwfbUkOqcQFI+uD"
        ) {}

        initView()

        initTracker()

        trekAd = AotterTrek.trekService(this)

        trekAd2 = AotterTrek.trekService(this)

        getAd()
        getAd2()

    }


    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {
            getAd()
            getAd2()
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

        trekAd.setTrekAdStatusListener(object : TrekAdStatusCallBack {
            override fun onAdError(message: String) {
                Log.e("onAdError", message)
            }

            override fun onAdLoaded(adData: AdData) {

                list[1] =
                    LocalNativeAdData(adData.title, adData.advertiserName, adData.imgIcon, true)

                nativeAdAdapter.setOnAdViewRegisteredListener(object :
                    OnNativeAdViewRegisteredListener {

                    override fun onAdViewRegistered(view: View) {
                        trekAd.registerNativeAd(view.context, view, adData)
                    }

                })

                nativeAdAdapter.update(list)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked(adData: AdData) {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression(view: View) {
                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news").applyTrekAd()

    }

    private fun getAd2() {

        trekAd2.setTrekAdStatusListener(object : TrekAdStatusCallBack {
            override fun onAdError(message: String) {
                Log.e("onAdError", message)
            }

            override fun onAdLoaded(adData: AdData) {


                list[list.size - 2] =
                    LocalNativeAdData(adData.title, adData.advertiserName, adData.imgIcon, true)

                nativeAdAdapter.setOnAdViewRegisteredListener(object :
                    OnNativeAdViewRegisteredListener {

                    override fun onAdViewRegistered(view: View) {
                        trekAd2.registerNativeAd(view.context, view, adData)
                    }

                })

                nativeAdAdapter.update(list)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked(adData: AdData) {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression(view: View) {
                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd2.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news")
            .applyTrekAd()

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