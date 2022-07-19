package com.aotter.trek.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.dto.Entity
import com.aotter.net.dto.TrekJsonObject
import com.aotter.net.dto.User
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.TrekDataKey
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdLoader
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.net.trek.ads.ad_view_binding.TrekAdViewBinder
import com.aotter.net.trek.sealed.ActionType
import com.aotter.net.trek.sealed.EntityType
import com.aotter.net.trek.tracker.Tracker
import com.aotter.trek.demo.databinding.ActivityNativeAdScrollPageBinding
import com.bumptech.glide.Glide


class TrekNativeAdScrollPageActivity : AppCompatActivity() {

    private val TAG = TrekNativeAdScrollPageActivity::class.java.simpleName

    private lateinit var viewBinding: ActivityNativeAdScrollPageBinding

    private lateinit var trekAdLoader: TrekAdLoader

    private lateinit var trekAdLoader2: TrekAdLoader

    private val trekAdRequest = TrekAdRequest.Builder()
        .setCategory("news")
        .setContentUrl("https://agirls.aotter.net/")
        .setContentTitle("電獺少女")
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdScrollPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        initTracker()

        getAd()

        getAd2()

    }

    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            TrekAdViewBinder.destroyAll()

            trekAdLoader.loadAd(trekAdRequest)

            trekAdLoader2.loadAd(trekAdRequest)

        }

    }

    private fun getAd2() {

        trekAdLoader =
            TrekAdLoader.Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        viewBinding.advertiser10.text = trekNativeAd.advertiserName

                        viewBinding.adTitle10.text = trekNativeAd.title

                        Glide.with(this@TrekNativeAdScrollPageActivity)
                            .load(trekNativeAd.imgIcon.drawable)
                            .into(viewBinding.adImg10)

                        TrekAdViewBinder.registerAdView(
                            viewBinding.nativeViewTen,
                            null,
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

    private fun getAd() {

        trekAdLoader2 =
            TrekAdLoader.Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader2 : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        viewBinding.advertiser2.text = trekNativeAd.advertiserName

                        viewBinding.adTitle2.text = trekNativeAd.title

                        Glide.with(this@TrekNativeAdScrollPageActivity)
                            .load(trekNativeAd.imgIcon.drawable)
                            .into(viewBinding.adImg2)

                        TrekAdViewBinder.registerAdView(
                            viewBinding.nativeViewTwo,
                            null,
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

    private fun initTracker() {

        val tracker = Tracker(this)

        val jsonObject = TrekJsonObject()

        jsonObject.put(TrekDataKey.REFERENCE, "disp")
        jsonObject.put(TrekDataKey.PUBLISHED_DATE, 1438090882490L)
        jsonObject.put(TrekDataKey.IMG, "http://pnn.aotter.net/Media/show/cna.jpg")
        jsonObject.put(TrekDataKey.AUTHOR, "skybear")
        jsonObject.put(TrekDataKey.ADDRESS, "105台北市松山區南京東路四段2號")
        jsonObject.put(TrekDataKey.LAT, 25.0463684)
        jsonObject.put(TrekDataKey.LNG, 121.5501565)

        val entity = Entity(
            "anthony", "anthony", EntityType.GAME.type, "anthony", listOf("News"),
            listOf(
                "News",
                "News_domestic"
            ),
            jsonObject,
        )

        val user = User("1991/10/10", "a1111111@gmail.com", "", "M", "0900000000", TrekJsonObject())

        tracker
            .timeSpan(1)
            .setUser(user)
            .setEntity(entity)
            .setActionType(ActionType.READ_POST)
            .sendTrackerReport()

    }

    override fun onDestroy() {
        super.onDestroy()

        TrekAdViewBinder.destroyAll()

    }


}