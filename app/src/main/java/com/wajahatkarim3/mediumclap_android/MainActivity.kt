package com.wajahatkarim3.mediumclap_android

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.wajahatkarim3.clapfab.ClapFAB
import com.wajahatkarim3.mediumclap_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding
    private var clapCount = 0
    private var isTranslateAnimActive = false
    private var isAlphaAnimActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bi.fabDemoClap.setOnClickListener {
            onClapButtonClick()
        }
        bi.fabDemoClap.visibility = View.GONE

        bi.clapFab.clapListener = object : ClapFAB.OnClapListener{
            override fun onFabClapped(clapFab: ClapFAB, count: Int, isMaxReached: Boolean) {
                Toast.makeText(this@MainActivity, "Clapped: $count with $isMaxReached" , Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onClapButtonClick()
    {
        clapCount++

        // Update clap icon depending on clap count
        if (clapCount > 0)
        {
            bi.fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
        }

        showCircleCount()
    }

    private fun showCircleCount()
    {
        bi.txtCountCircle.text = "+$clapCount"
        bi.txtCountCircle.visibility = View.VISIBLE
        bi.txtCountCircle.y = bi.fabDemoClap.y
        bi.txtCountCircle.alpha = 1f
        

        if (isTranslateAnimActive) return

        val showAnim = ObjectAnimator.ofFloat(bi.txtCountCircle, "translationY", -200f)
        showAnim.duration = 200
        showAnim.addListener(MyAnimatorListener{
            setOnAnimationEnd {
                isTranslateAnimActive = false
            }
        })

        val hideAnim = ObjectAnimator.ofFloat(bi.txtCountCircle, "alpha", 0f)
        hideAnim.duration = 150
        hideAnim.startDelay = 200
        hideAnim.addListener(MyAnimatorListener{
            setOnAnimationEnd {
                isAlphaAnimActive = false
            }
        })

        /*
        var hideAnim = bi.txtCountCircle.animate()
                .alpha(0f)
                .setInterpolator(DecelerateInterpolator())
                .setDuration(150)
                */

        isTranslateAnimActive = true
        isAlphaAnimActive = true

        val animSet = AnimatorSet()
        animSet.playSequentially(showAnim, hideAnim)
        animSet.addListener(MyAnimatorListener{
            setOnAnimationEnd {
                resetCircleCountTextView()
            }
        })
        animSet.start()
    }

    private fun resetCircleCountTextView()
    {
        bi.txtCountCircle.y = bi.fabDemoClap.y
        bi.txtCountCircle.visibility = View.GONE
        bi.txtCountCircle.alpha = 1f
    }
}
