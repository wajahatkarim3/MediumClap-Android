package com.wajahatkarim3.mediumclap_android

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wajahatkarim3.mediumclap_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
