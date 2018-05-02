package com.d42gmail.cavar.beforeandafter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beforeAndAfterView.loadImagesByUrl("http://zg.plavatvornica.com/zrinjevac/now_then/1.jpg", "http://zg.plavatvornica.com/zrinjevac/now_then/2.jpg")
        beforeAndAfterView.setRoundCorners(true)
    }
}
