package com.d42gmail.cavar.beforeandafter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beforeAndAfterView.loadImagesBySrc(R.drawable.image_after, R.drawable.image_before)
    }
}
