package com.wajahatkarim3.mediumclap_android

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.databinding.DataBindingUtil
import android.databinding.adapters.ViewGroupBindingAdapter.setListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.wajahatkarim3.mediumclap_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding
    var clapCount = 0
    var initialCountY = 0f

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

        var showAnim = ObjectAnimator.ofFloat(bi.txtCountCircle, "translationY", -200f)
        var hideAnim = ObjectAnimator.ofFloat(bi.txtCountCircle, "alpha", 0f)

        /*
        var hideAnim = bi.txtCountCircle.animate()
                .alpha(0f)
                .setInterpolator(DecelerateInterpolator())
                .setDuration(150)
                */

        var animSet = AnimatorSet()
        animSet.playSequentially(showAnim, hideAnim)
        animSet.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                resetCircleCountTextView()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animSet.start()
    }

    fun resetCircleCountTextView()
    {
        bi.txtCountCircle.y = bi.fabDemoClap.y
        bi.txtCountCircle.visibility = View.GONE
        bi.txtCountCircle.alpha = 1f
    }
}
