package com.wajahatkarim3.mediumclap_android

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator
import com.wajahatkarim3.mediumclap_android.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    lateinit var bi: ActivityExampleBinding
    var clapCount = 0
    var isCirlceAvailable = false

    var hideAnimator: ViewAnimator? = null
    var textMoveUpAnimator: ViewAnimator? = null

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
        hideAnimator?.cancel()
        if (textMoveUpAnimator != null)
            return

        // Update clap icon depending on clap count
        if (clapCount > 0)
        {
            bi.fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
        }

        // 1. Scale Up FAB Button On Each Click
        ViewAnimator
                .animate(bi.fabDemoClap)
                    .scale(1f, 1.2f)
                    .duration(70)
                    //.interpolator(AccelerateDecelerateInterpolator())
                .thenAnimate(bi.fabDemoClap)
                    .scale(1.2f, 1.0f)
                    .duration(70)
                    //.interpolator(AccelerateDecelerateInterpolator())
                .start()

        // 2. Show and move count text circle from button

        // Reset Circle Text State
        bi.txtCountCircle.visibility = View.VISIBLE
        bi.txtCountCircle.y = bi.fabDemoClap.y + bi.fabDemoClap.height/2
        bi.txtCountCircle.alpha = 0f

        if (!isCirlceAvailable)
        {
            // Animate Text up
            textMoveUpAnimator = ViewAnimator
                    .animate(bi.txtCountCircle)
                    .dp().translationY(0f, -70f)
                    .interpolator(DecelerateInterpolator())
                    .alpha(0f, 1f)
                    .duration(500)
                    .onStop {
                        Log.w("ANIM", "Circle stopped!")
                        isCirlceAvailable = true
                        textMoveUpAnimator = null
                    }
                    .start()

            // Hide Circle Anim
            hideAnimator = ViewAnimator.animate(bi.txtCountCircle)
                    .alpha(1f, 0f)
                    .dp().translationY( -70f, -140f)
                    .duration(500)
                    .startDelay(500)
                    .onStop {
                        Log.e("ANIM", "Circle hidden")
                        isCirlceAvailable = false
                    }
                    .start()
        }
        else
        {
            // Scale up Circle Text
            ViewAnimator
                    .animate(bi.txtCountCircle)
                        .scale(1f, 1.2f)
                        .duration(70)
                    .thenAnimate(bi.fabDemoClap)
                        .scale(1.2f, 1.0f)
                        .duration(70)
                    .start()
        }



    }
}
