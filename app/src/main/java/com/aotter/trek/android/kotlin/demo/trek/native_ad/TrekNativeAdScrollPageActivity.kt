package com.aotter.trek.android.kotlin.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.dto.Entity
import com.aotter.net.dto.User
import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.AotterTrek
import com.aotter.net.trek.ads.TrekAd
import com.aotter.net.trek.ads.TrekAdStatusCallBack
import com.aotter.net.trek.sealed.ActionType
import com.aotter.net.trek.sealed.EntityType
import com.aotter.trek.demo.databinding.ActivityNativeAdScrollPageBinding
import com.bumptech.glide.Glide
import com.google.gson.JsonObject


class TrekNativeAdScrollPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNativeAdScrollPageBinding

    private lateinit var trekAd: TrekAd

    private lateinit var trekAd2: TrekAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdScrollPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        AotterTrek.initAotterService(
            this@TrekNativeAdScrollPageActivity,
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
            trekAd2.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news")
                .applyTrekAd()
            trekAd.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news")
                .applyTrekAd()
        }

    }

    private fun getAd2() {

        trekAd2.setTrekAdStatusListener(object : TrekAdStatusCallBack {
            override fun onAdError(message: String) {
                Log.e("onAdError", message)
            }

            override fun onAdLoaded(adData: AdData) {

                trekAd2.registerNativeAd(
                    this@TrekNativeAdScrollPageActivity,
                    viewBinding.nativeViewTen,
                    adData
                )

                viewBinding.advertiser10.text = adData.advertiserName

                viewBinding.adTitle10.text = adData.title

                Glide.with(this@TrekNativeAdScrollPageActivity)
                    .load(adData.imgIcon)
                    .into(viewBinding.adImg10)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked(adData: AdData) {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression(view: View) {
                Log.e("onAdImpression", "AD Impression success.")

            }

        })

        trekAd2.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news")
            .applyTrekAd()

    }

    private fun getAd() {

        trekAd.setTrekAdStatusListener(object : TrekAdStatusCallBack {
            override fun onAdError(message: String) {
                Log.e("onAdError", message)
            }

            override fun onAdLoaded(adData: AdData) {

                trekAd.registerNativeAd(
                    this@TrekNativeAdScrollPageActivity,
                    viewBinding.nativeViewTwo,
                    adData
                )

                viewBinding.advertiser2.text = adData.advertiserName

                viewBinding.adTitle2.text = adData.title

                Glide.with(this@TrekNativeAdScrollPageActivity)
                    .load(adData.imgIcon)
                    .into(viewBinding.adImg2)

                Log.e("onAdLoaded", "onAdLoaded")

            }

            override fun onAdClicked(adData: AdData) {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression(view: View) {
                Log.e("onAdImpression", "AD2 Impression success.")

            }

        })

        trekAd.setPlaceUid("45419fb5-a846-4c4a-837f-3b391ec7b45a").setCategory("news").applyTrekAd()

    }

    private fun initTracker() {

        val tracker = AotterTrek.trackerService(this)

        val jsonObject = JsonObject()

        jsonObject.addProperty("aaaa", "dsadsadsada")
        jsonObject.addProperty("ssss", "dsadsadsada")

        val entity = Entity(
            "anthony", "anthony", EntityType.GAME.type, "anthony", listOf("News"),
            listOf(
                "News",
                "News_domestic"
            ),
            jsonObject,
        )

        val user = User("1991/10/10", "a1111111@gmail.com", "", "M", JsonObject(), "0900000000")

        tracker
            .timeSpan(1)
            .setUser(user)
            .setEntity(entity)
            .setActionType(ActionType.READ_POST)
            .sendTrackerReport()

    }


}