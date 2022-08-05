package com.aotter.trek.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.trek.TrekAds
import com.aotter.trek.demo.databinding.ActivityMainBinding
import com.aotter.trek.demo.trek.banner_ad.TrekBannerAdScrollPageActivity
import com.aotter.trek.demo.trek.native_ad.TrekNativeAdRecyclerViewPageActivity
import com.aotter.trek.demo.trek.native_ad.TrekNativeAdScrollPageActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        TrekAds.initialize(
            this,
            "DNgNhOwfbUkOqcQFI+uD"
        ) {}

        initView()


    }

    private fun initView() {

        viewBinding.nativeAdRecyclerViewBtn.setOnClickListener {

            val intent = Intent()
            intent.setClass(this, TrekNativeAdRecyclerViewPageActivity::class.java)
            startActivity(intent)

        }

        viewBinding.nativeAdScrollPageBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekNativeAdScrollPageActivity::class.java)
            startActivity(intent)
        }


        viewBinding.bannerAdScrollPageBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekBannerAdScrollPageActivity::class.java)
            startActivity(intent)
        }


    }

}