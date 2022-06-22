package com.aotter.trek.android.kotlin.demo.trek.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aotter.net.trek.AotterTrek
import com.aotter.trek.demo.databinding.ActivityMainBinding
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.banner_ad.TrekBannerAdScrollPageActivity
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.native_ad.TrekNativeAdRecyclerViewPageActivity
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.native_ad.TrekNativeAdScrollPageActivity
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.supr_ad.TrekMediaRecyclerViewPageActivity
import com.aotter.trek.android.kotlin.demo.trek.demo.trek.supr_ad.TrekMediaScrollPageActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        AotterTrek.initialize(
            this,
            "DNgNhOwfbUkOqcQFI+uD"
        ) {}

        initView()


    }

    private fun initView() {

        viewBinding.trekNativeAdRecyclerViewBtn.setOnClickListener {

            val intent = Intent()
            intent.setClass(this, TrekNativeAdRecyclerViewPageActivity::class.java)
            startActivity(intent)

        }

        viewBinding.trekNativeAdScrollPageBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekNativeAdScrollPageActivity::class.java)
            startActivity(intent)
        }

        viewBinding.trekMediaAdScrollPageAdBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekMediaScrollPageActivity::class.java)
            startActivity(intent)
        }

        viewBinding.trekMediaAdRecyclerViewAdBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekMediaRecyclerViewPageActivity::class.java)
            startActivity(intent)
        }


        viewBinding.trekBannerAdScrollPageAdBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TrekBannerAdScrollPageActivity::class.java)
            startActivity(intent)
        }


    }

}