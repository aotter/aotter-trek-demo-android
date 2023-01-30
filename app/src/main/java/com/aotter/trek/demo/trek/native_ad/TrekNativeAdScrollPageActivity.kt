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
import com.aotter.net.trek.sealed.ActionType
import com.aotter.net.trek.sealed.EntityType
import com.aotter.net.trek.tracker.Tracker
import com.aotter.trek.demo.databinding.ActivityNativeAdScrollViewBinding
import com.aotter.trek.demo.databinding.ItemStyle1Binding
import com.aotter.trek.demo.databinding.ItemStyle2Binding
import com.aotter.trek.demo.databinding.ItemStyle3Binding
import com.bumptech.glide.Glide

class TrekNativeAdScrollPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNativeAdScrollViewBinding

    private var adView: ItemStyle1Binding? = null

    private var adView2: ItemStyle2Binding? = null

    private var adView3: ItemStyle3Binding? = null

    private val tracker = Tracker(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdScrollViewBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initTracker()

        getAd()

        getAd2()

        getAd3()

    }

    private fun getAd() {

       val trekAdLoader = TrekAdLoader
           .Builder(this, "81608f91-8b2b-4f8f-86a1-539a1959f836")
           .withAdListener(object : TrekAdListener {
               override fun onAdFailedToLoad(message: String) {
                   super.onAdFailedToLoad(message)

               }

               override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                   super.onAdLoaded(trekNativeAd)

                    if (!isDestroyed) {

                        adView = ItemStyle1Binding.bind(viewBinding.viewStub.inflate()).apply {

                            advertiser.text = trekNativeAd.advertiserName

                            adBody.text = trekNativeAd.text

                            trekNativeAdView.setTrekMediaView(trekMediaView)

                            trekNativeAdView.setNativeAd(trekNativeAd)

                        }

                    }

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
            .build()

        val trekAdRequest = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdLoader.loadAd(trekAdRequest)

    }

    private fun getAd2() {

        val trekAdLoader = TrekAdLoader
            .Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
            .withAdListener(object : TrekAdListener {
                override fun onAdFailedToLoad(message: String) {
                    super.onAdFailedToLoad(message)

                }

                override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                    super.onAdLoaded(trekNativeAd)

                    if (!isDestroyed) {

                        adView2 = ItemStyle2Binding.bind(viewBinding.viewStub2.inflate()).apply {

                            advertiser.text = trekNativeAd.advertiserName

                            adBody.text = trekNativeAd.text

                            Glide.with(this@TrekNativeAdScrollPageActivity)
                                .load(trekNativeAd.imgMain.drawable)
                                .into(adImg)

                            trekNativeAdView.setNativeAd(trekNativeAd)

                        }

                    }

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
            .build()

        val trekAdRequest = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdLoader.loadAd(trekAdRequest)

    }

    private fun getAd3() {

        val trekAdLoader = TrekAdLoader
            .Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
            .withAdListener(object : TrekAdListener {
                override fun onAdFailedToLoad(message: String) {
                    super.onAdFailedToLoad(message)


                }

                override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                    super.onAdLoaded(trekNativeAd)

                    if (!isDestroyed) {

                        adView3 = ItemStyle3Binding.bind(viewBinding.viewStub3.inflate()).apply {

                            advertiser.text = trekNativeAd.advertiserName

                            adBody.text = trekNativeAd.text

                            Glide.with(this@TrekNativeAdScrollPageActivity)
                                .load(trekNativeAd.imgIconHd.drawable)
                                .into(adImg)

                            trekNativeAdView.setNativeAd(trekNativeAd)

                        }

                    }

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
            .build()

        val trekAdRequest = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdLoader.loadAd(trekAdRequest)

    }

    private fun initTracker() {

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

        val user = User(
            "1991/10/10", "a1111111@gmail.com", "", "M", "0900000000",
            TrekJsonObject()
        )

        tracker
            .timeSpan(1)
            .setUser(user)
            .setEntity(entity)
            .setActionType(ActionType.READ_POST)
            .sendTrackerReport()

    }

    override fun onDestroy() {
        super.onDestroy()

        adView?.trekNativeAdView?.destroy()

        adView2?.trekNativeAdView?.destroy()

        adView3?.trekNativeAdView?.destroy()

    }

}