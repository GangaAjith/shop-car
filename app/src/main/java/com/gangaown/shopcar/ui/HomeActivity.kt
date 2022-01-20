package com.gangaown.shopcar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gangaown.shopcar.R


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}

