package com.aotter.trek.android.kotlin.demo.trek.demo.trek.supr_ad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aotter.net.dto.mftc.response.AdData
import com.aotter.net.trek.AotterTrek
import com.aotter.net.trek.ads.TrekAd
import com.aotter.net.trek.ads.TrekAdListener
import com.aotter.net.trek.ads.TrekAdRequest
import com.aotter.trek.demo.databinding.ActivityVastRecyclerViewPageBinding
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.CoverPageActivity
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.LocalSuprAdData
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.SuprAdAdapter

class TrekMediaRecyclerViewPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityVastRecyclerViewPageBinding

    private lateinit var trekAd: TrekAd

    private lateinit var trekAd2: TrekAd

    private val trekAdRequest = TrekAdRequest.Builder()
        .setCategory("news")
        .setContentUrl("https://agirls.aotter.net/")
        .setContentTitle("電獺少女")
        .build()

    private lateinit var suprAdAdapter: SuprAdAdapter

    private var list: MutableList<LocalSuprAdData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityVastRecyclerViewPageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        trekAd = AotterTrek.trekService(this)

        trekAd2 = AotterTrek.trekService(this)

        initView()

        getAd()

    }


    private fun initView() {

        viewBinding.refreshBtn.setOnClickListener {

            trekAd.setPlaceUid("81608f91-8b2b-4f8f-86a1-539a1959f836").loadAd(trekAdRequest)

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

        trekAd.setTrekAdListener(object : TrekAdListener {
            override fun onAdFailedToLoad(message: String) {
                Log.e("onAdError", message)

                getAd2()
            }

            override fun onAdLoaded(adData: AdData) {

                list[1].trekAd?.let {
                    list[1] = LocalSuprAdData(adData.title, adData.advertiserName, trekAd, adData)
                } ?: kotlin.run {
                    list.add(
                        1,
                        LocalSuprAdData(adData.title, adData.advertiserName, trekAd, adData)
                    )
                }

                getAd2()

            }

            override fun onAdClicked() {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression() {

                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd.setPlaceUid("81608f91-8b2b-4f8f-86a1-539a1959f836").loadAd(trekAdRequest)

    }


    private fun getAd2() {

        trekAd2.setTrekAdListener(object : TrekAdListener {
            override fun onAdFailedToLoad(message: String) {
                Log.e("onAdError", message)

                suprAdAdapter.update(list)

            }

            override fun onAdLoaded(adData: AdData) {

                list[8].trekAd?.let {
                    list[8] = LocalSuprAdData(adData.title, adData.advertiserName, trekAd, adData)
                } ?: kotlin.run {
                    list.add(
                        8,
                        LocalSuprAdData(adData.title, adData.advertiserName, trekAd, adData)
                    )
                }

                suprAdAdapter.update(list)

            }

            override fun onAdClicked() {
                Log.e("onAdClicked", "AdClicked success.")
            }

            override fun onAdImpression() {

                Log.e("onAdImpression", "Impression success.")

            }

        })

        trekAd2.setPlaceUid("81608f91-8b2b-4f8f-86a1-539a1959f836").loadAd(trekAdRequest)

    }

}