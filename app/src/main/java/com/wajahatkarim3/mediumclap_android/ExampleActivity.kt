package com.wajahatkarim3.mediumclap_android

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import com.github.florent37.viewanimator.ViewAnimator
import com.wajahatkarim3.mediumclap_android.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    lateinit var bi: ActivityExampleBinding
    var clapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_example)

        bi.fabDemoClap.setOnClickListener {
            clapCount++
            animateClapFab()
        }
    }

    fun animateClapFab()
    {
        // Update clap icon depending on clap count
        if (clapCount > 0)
        {
            bi.fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
        }

        //1. Scale Up FAB Button On Each Click
        ViewAnimator
                .animate(bi.fabDemoClap)
                    .scale(1f, 1.1f)
                    .duration(70)
                    .interpolator(AccelerateDecelerateInterpolator())
                .thenAnimate(bi.fabDemoClap)
                    .scale(1.1f, 1.0f)
                    .duration(70)
                    .interpolator(AccelerateDecelerateInterpolator())
                .start()

    }
}
