package com.wajahatkarim3.mediumclap_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        //Set max clap count
        clapFAB4.maxCount = 20000

        //Set Default clap count
        clapFAB4.setClapCount(9999)
    }

    private val listener = object : ClapFAB.OnClapListener{
        override fun onFabClapped(clapFab: ClapFAB, count: Int, isMaxReached: Boolean) {
            if(isMaxReached) {
                Toast.makeText(this@DemoActivity, "Clapped: $count of ${clapFab.maxCount}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
