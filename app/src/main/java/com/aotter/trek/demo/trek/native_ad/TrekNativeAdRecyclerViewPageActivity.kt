package com.aotter.trek.demo.trek.native_ad

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.trek.response.TrekNativeAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdLoader
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.trek.demo.R
import com.aotter.trek.demo.databinding.ActivityNativeAdRecyclerviewViewBinding
import com.aotter.trek.demo.databinding.ItemNativeAdBinding
import com.aotter.trek.demo.databinding.ItemStyle3Binding
import com.aotter.trek.demo.trek.ItemCallback
import com.aotter.trek.demo.trek.LocalNativeAdData
import com.aotter.trek.demo.trek.NativeAdAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class TrekNativeAdRecyclerViewPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNativeAdRecyclerviewViewBinding

    private var nativeAdAdapter = NativeAdAdapter(ItemCallback())

    private var trekAdLoader: TrekAdLoader? = null

    private var trekAdRequest: TrekAdRequest? = null

    private var trekAdLoader2: TrekAdLoader? = null

    private var trekAdRequest2: TrekAdRequest? = null

    private var list: MutableList<LocalNativeAdData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNativeAdRecyclerviewViewBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        initRecyclerView()

    }

    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            nativeAdAdapter.submitList(null) {
                initRecyclerView()
            }

        }

    }


    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        viewBinding.nativeAdRecyclerView.layoutManager = linearLayoutManager

        viewBinding.nativeAdRecyclerView.adapter = nativeAdAdapter

        list = mutableListOf<LocalNativeAdData>()

        repeat(12) {

            val data = LocalNativeAdData()

            data.postId = data.hashCode()

            list.add(
                data
            )

        }

        nativeAdAdapter.submitList(list.toList()) {
            getAd()
        }


    }

    private fun getAd() {

        trekAdLoader = TrekAdLoader
            .Builder(this, "45419fb5-a846-4c4a-837f-3b391ec7b45a")
            .withAdListener(object : TrekAdListener {
                override fun onAdFailedToLoad(message: String) {
                    super.onAdFailedToLoad(message)

                }

                override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                    super.onAdLoaded(trekNativeAd)

                    if (!isDestroyed) {

                        val data = LocalNativeAdData()

                        data.postId = trekNativeAd.hashCode()

                        data.adView = createAdView2(trekNativeAd)

                        list.add(4, data)

                        nativeAdAdapter.submitList(list.toList()) {

                            getAd2()

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

        trekAdRequest = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdRequest?.let {
            trekAdLoader?.loadAd(it)
        }

    }

    private fun getAd2() {

        trekAdLoader2 = TrekAdLoader
            .Builder(this, "81608f91-8b2b-4f8f-86a1-539a1959f836")
            .withAdListener(object : TrekAdListener {
                override fun onAdFailedToLoad(message: String) {
                    super.onAdFailedToLoad(message)

                }

                override fun onAdLoaded(trekNativeAd: TrekNativeAd) {
                    super.onAdLoaded(trekNativeAd)
                    if (!isDestroyed) {

                        val data = LocalNativeAdData()

                        data.postId = trekNativeAd.hashCode()

                        data.adView = createAdView(trekNativeAd)

                        list.add(8, data)

                        nativeAdAdapter.submitList(list.toList())

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

        trekAdRequest2 = TrekAdRequest.Builder()
            .setCategory("NEWS")
            .setContentUrl("https://agirls.aotter.net/")
            .setContentTitle("電獺少女")
            .build()

        trekAdRequest2?.let {
            trekAdLoader2?.loadAd(it)
        }

    }

    private fun createAdView2(trekNativeAd: TrekNativeAd): View {

        val adView = ItemStyle3Binding.bind(
            LayoutInflater.from(this@TrekNativeAdRecyclerViewPageActivity)
                .inflate(R.layout.item_style3, null)
        )

        adView.advertiser.text = trekNativeAd.advertiserName

        adView.adBody.text = trekNativeAd.text

        Glide.with(this@TrekNativeAdRecyclerViewPageActivity)
            .load(trekNativeAd.imgIconHd.drawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(adView.adImg)

        adView.trekNativeAdView.setNativeAd(trekNativeAd)

        ////////

        return adView.root

    }

    private fun createAdView(trekNativeAd: TrekNativeAd): View {

        val adView = ItemNativeAdBinding.bind(
            LayoutInflater.from(this@TrekNativeAdRecyclerViewPageActivity)
                .inflate(R.layout.item_native_ad, null)
        )

        adView.advertiser.text = trekNativeAd.advertiserName

        adView.adBody.text = trekNativeAd.text

        Glide.with(this@TrekNativeAdRecyclerViewPageActivity)
            .load(trekNativeAd.imgIconHd.drawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(adView.adImg)

        adView.trekNativeAdView.setTrekMediaView(adView.trekMediaView)

        adView.trekNativeAdView.setNativeAd(trekNativeAd)

        return adView.root

    }

}