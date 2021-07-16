package com.aotter.trek.android.kotlin.demo.trek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aotter.trek.demo.databinding.ActivityCoverPageBinding

class CoverPageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCoverPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCoverPageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}