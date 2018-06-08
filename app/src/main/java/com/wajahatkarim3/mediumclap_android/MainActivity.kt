package com.wajahatkarim3.mediumclap_android

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wajahatkarim3.mediumclap_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding
    var clapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bi.fabDemoClap.setOnClickListener {
            onClapButtonClick()
        }
    }

    fun onClapButtonClick()
    {
        clapCount++

        // Update clap icon depending on clap count
        if (clapCount > 0)
        {
            bi.fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
        }

        showCircleCount()
    }

    fun showCircleCount()
    {
        bi.txtCountCircle.text = "+" + clapCount.toString()
        bi.txtCountCircle.visibility = View.VISIBLE
    }
}
