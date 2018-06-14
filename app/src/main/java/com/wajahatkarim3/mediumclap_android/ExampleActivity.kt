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
import com.github.florent37.viewanimator.AnimationBuilder
import com.github.florent37.viewanimator.ViewAnimator
import com.wajahatkarim3.mediumclap_android.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    lateinit var bi: ActivityExampleBinding
    var clapCount = 0
    var isCirlceAvailable = false

    var hideAnimator: ViewAnimator? = null
    var textMoveUpAnimator: ViewAnimator? = null

    // Animations
    var fabScaleAnimation_1: ViewAnimator? = null
    var circleShowMoveUpAnimation_2: ViewAnimator? = null
    var circleScaleAnimation_3: ViewAnimator? = null
    var circleHideMoveAnimation_4: ViewAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_example)

        bi.fabDemoClap.setOnClickListener {

            clapCount++
            bi.txtCountCircle.text = "+$clapCount"
            if (clapCount > 0)
            {
                bi.fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
            }

            playActualFabAnim()
        }
    }

    fun playActualFabAnim()
    {
        // 1. Scale Up FAB Button On Each Click
        fabScaleUpAnimation()

        // 2. Show and move count text circle from button
        // If circle is not shown on top, then move up and show circle
        if (circleShowMoveUpAnimation_2 == null && !isCirlceAvailable)
        {
            circleHideMoveAnimation_4?.cancel()
            circleShowMoveUpAnimation()
        }
        // Else, scale up the counter text
        else
        {
            circleHideMoveAnimation_4?.cancel()
            circleScaleAnimation()
        }

    }


    fun fabScaleUpAnimation()
    {
        fabScaleAnimation_1 = ViewAnimator
                .animate(bi.fabDemoClap)
                    .scale(1f, 1.2f)
                    .duration(70)
                .thenAnimate(bi.fabDemoClap)
                    .scale(1.2f, 1.0f)
                    .duration(70)
                .start()
                .onStop {
                    fabScaleAnimation_1 = null
                }
    }

    fun circleShowMoveUpAnimation()
    {
        bi.txtCountCircle.visibility = View.VISIBLE
        bi.txtCountCircle.y = bi.fabDemoClap.y + bi.fabDemoClap.height/2
        bi.txtCountCircle.alpha = 0f
        circleShowMoveUpAnimation_2 = ViewAnimator
                .animate(bi.txtCountCircle)
                .dp().translationY(0f, -70f)
                .interpolator(DecelerateInterpolator())
                .alpha(0f, 1f)
                .duration(500)
                .onStop {
                    isCirlceAvailable = true
                    //circleShowMoveUpAnimation_2 = null
                    circleHideMoveAnimation()
                }
                .start()
    }

    fun circleScaleAnimation()
    {
        circleScaleAnimation_3 = ViewAnimator
                .animate(bi.txtCountCircle)
                .scale(1f, 1.2f)
                .duration(70)
                .thenAnimate(bi.txtCountCircle)
                .scale(1.2f, 1.0f)
                .duration(70)
                .onStop {
                    // Hide Circle Anim
                    circleScaleAnimation_3 = null
                    circleHideMoveAnimation()
                }
                .start()
    }

    fun circleHideMoveAnimation()
    {
        circleHideMoveAnimation_4?.cancel()
        circleHideMoveAnimation_4 = ViewAnimator
                .animate(bi.txtCountCircle)
                .alpha(1f, 0f)
                .dp().translationY( -70f, -140f)
                .duration(500)
                .startDelay(1500)
                .onStop {
                    isCirlceAvailable = false
                    circleShowMoveUpAnimation_2 = null
                    circleHideMoveAnimation_4 = null
                }
                .start()
    }

    fun animateClapFab()
    {
        if (hideAnimator != null)
        {
            hideAnimator?.cancel()
            isCirlceAvailable = true
        }
        else
        {
            isCirlceAvailable = false
            hideAnimator?.cancel()
        }

        if (textMoveUpAnimator != null)
            return

        // Update clap icon depending on clap count
        bi.txtCountCircle.text = "+$clapCount"
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
                        //isCirlceAvailable = false
                    }
                    .start()
        }
        else
        {
            // Scale up Circle Text
            bi.txtCountCircle.alpha = 1f
            ViewAnimator
                    .animate(bi.txtCountCircle)
                        .scale(1f, 1.2f)
                        .duration(70)
                    .thenAnimate(bi.txtCountCircle)
                        .scale(1.2f, 1.0f)
                        .duration(70)
                    .onStop {

                        // Hide Circle Anim
                        hideAnimator = ViewAnimator.animate(bi.txtCountCircle)
                                .alpha(1f, 0f)
                                .dp().translationY( -70f, -140f)
                                .duration(500)
                                .startDelay(500)
                                .onStop {
                                    Log.e("ANIM", "Circle hidden")
                                    //isCirlceAvailable = false
                                }
                                .start()
                    }
                    .start()


        }



    }
}
