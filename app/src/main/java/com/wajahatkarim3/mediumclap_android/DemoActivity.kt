package com.wajahatkarim3.mediumclap_android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wajahatkarim3.clapfab.ClapFAB
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        clapFAB.clapListener = listener
        clapFAB2.clapListener = listener
        clapFAB3.clapListener = listener
        clapFAB4.clapListener = listener
    }

    private val listener = object : ClapFAB.OnClapListener{
        override fun onFabClapped(clapFab: ClapFAB, count: Int, isMaxReached: Boolean) {
            if(isMaxReached) {
                Toast.makeText(this@DemoActivity, "Clapped: $count of ${clapFab.maxCount}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
