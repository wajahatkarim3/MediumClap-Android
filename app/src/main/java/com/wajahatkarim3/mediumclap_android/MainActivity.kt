package com.wajahatkarim3.mediumclap_android

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.clapfab.ClapFAB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.txtCountCircle

class MainActivity : AppCompatActivity() {

    private var clapCount = 0
    private var isTranslateAnimActive = false
    private var isAlphaAnimActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clapFab.clapListener = object : ClapFAB.OnClapListener{
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
            fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
        }

        showCircleCount()
    }

    private fun showCircleCount()
    {
        txtCountCircle.text = "+$clapCount"
        txtCountCircle.visibility = View.VISIBLE
        txtCountCircle.y = fabDemoClap.y
        txtCountCircle.alpha = 1f
        

        if (isTranslateAnimActive) return

        val showAnim = ObjectAnimator.ofFloat(txtCountCircle, "translationY", -200f)
        showAnim.duration = 200
        showAnim.addListener(MyAnimatorListener{
            setOnAnimationEnd {
                isTranslateAnimActive = false
            }
        })

        val hideAnim = ObjectAnimator.ofFloat(txtCountCircle, "alpha", 0f)
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
        txtCountCircle.y = fabDemoClap.y
        txtCountCircle.visibility = View.GONE
        txtCountCircle.alpha = 1f
    }
}
