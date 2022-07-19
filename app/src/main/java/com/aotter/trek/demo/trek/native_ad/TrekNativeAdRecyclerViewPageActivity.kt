package com.aotter.trek.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.Entity
import com.aotter.net.dto.Location
import com.aotter.net.dto.TrekJsonObject
import com.aotter.net.dto.User
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdLoader
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.net.trek.ads.ad_view_binding.TrekAdViewBinder
import com.aotter.net.trek.annomation.EntityType
import com.aotter.net.trek.sealed.ActionType
import com.aotter.net.trek.tracker.Tracker
import com.aotter.trek.demo.databinding.ActivityNativeAdRecyclerviewPageBinding
import com.aotter.trek.demo.trek.LocalNativeAdData
import com.aotter.trek.demo.trek.NativeAdAdapter

class TrekNativeAdRecyclerViewPageActivity : AppCompatActivity() {

    private val TAG = TrekNativeAdScrollPageActivity::class.java.simpleName

    private lateinit var viewBinding: ActivityNativeAdRecyclerviewPageBinding

    private lateinit var trekAdLoader: TrekAdLoader

    private lateinit var trekAdLoader2: TrekAdLoader

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

        getAd()

    }


    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            TrekAdViewBinder.destroyAll()

            trekAdLoader.loadAd(trekAdRequest)

            trekAdLoader2.loadAd(trekAdRequest)

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

        trekAdLoader =
            TrekAdLoader.Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader2 : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        list[1].trekNativeAd?.let {
                            list[1] = LocalNativeAdData(
                                trekNativeAd.title,
                                trekNativeAd.advertiserName,
                                trekNativeAd.imgIcon.uri.toString(),
                                trekNativeAd
                            )
                        } ?: kotlin.run {
                            list.add(
                                1,
                                LocalNativeAdData(
                                    trekNativeAd.title,
                                    trekNativeAd.advertiserName,
                                    trekNativeAd.imgIcon.uri.toString(),
                                    trekNativeAd
                                )
                            )
                        }

                        getAd2()


                    }

                    override fun onAdClicked() {
                        Log.e(TAG, "trekAdLoader2 : Ad Clicked success.")
                    }

                    override fun onAdImpression() {

                        Log.e(TAG, "trekAdLoader2 : AD Impression success.")

                    }

                })
                .build()

        trekAdLoader.loadAd(trekAdRequest)

    }

    private fun getAd2() {

        trekAdLoader2 =
            TrekAdLoader.Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
                .withAdListener(object : TrekAdListener {
                    override fun onAdFailedToLoad(message: String) {
                        Log.e(TAG, "trekAdLoader2 : $message")
                    }

                    override fun onAdLoaded(trekNativeAd: TrekNativeAd) {

                        list[8].trekNativeAd?.let {
                            list[8] = LocalNativeAdData(
                                trekNativeAd.title,
                                trekNativeAd.advertiserName,
                                trekNativeAd.imgIcon.uri.toString(),
                                trekNativeAd
                            )
                        } ?: kotlin.run {
                            list.add(
                                8,
                                LocalNativeAdData(
                                    trekNativeAd.title,
                                    trekNativeAd.advertiserName,
                                    trekNativeAd.imgIcon.uri.toString(),
                                    trekNativeAd
                                )
                            )
                        }

                        nativeAdAdapter.update(list)


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

        val entity = Entity(
            "anthony", "anthony", EntityType.PLACE, "anthony", listOf("炸雞"),
            listOf(
                "News",
                "News_domestic"
            ),
            TrekJsonObject(),
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

    override fun onDestroy() {
        super.onDestroy()

        TrekAdViewBinder.destroyAll()

    }


}