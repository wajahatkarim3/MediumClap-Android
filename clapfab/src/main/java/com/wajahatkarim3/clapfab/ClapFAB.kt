package com.wajahatkarim3.clapfab

import android.animation.ObjectAnimator
import android.content.Context
import android.os.CountDownTimer
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator

/**
 * Created by wajah on 2/7/2018.
 */
class ClapFAB : RelativeLayout
{
    val TAG = ClapFAB::class.simpleName

    // Data Values
    private var clapCount = 0
    private var isCirlceAvailable = false
    private var isHideAnimStopped = false

    // Animations
    var fabScaleAnimation_1: ViewAnimator? = null
    var circleShowMoveUpAnimation_2: ViewAnimator? = null
    var circleScaleAnimation_3: ViewAnimator? = null
    var circleHideMoveAnimation_4: ViewAnimator? = null

    // Views
    lateinit var txtCountCircle: TextView
    lateinit var dotsView: DotsView
    lateinit var fabDemoClap: FloatingActionButton


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    {
        init(context, attrs)
    }

    fun init(context: Context, attributes: AttributeSet?)
    {
        attributes?.let { attrs ->
            // Getting the views
            inflate(context, R.layout.clap_fab_layout, this)
            txtCountCircle = findViewById<TextView>(R.id.txtCountCircle)
            fabDemoClap = findViewById(R.id.fabDemoClap)
            dotsView = findViewById(R.id.dotsView)

            // Setting Listener
            fabDemoClap.setOnClickListener {
                clapCount++
                txtCountCircle.text = "+$clapCount"
                if (clapCount > 0)
                {
                    fabDemoClap.setImageResource(R.drawable.ic_clap_hands_filled)
                }

                playActualFabAnim()
            }

            // Set Default Values Here

            // Check for attributes
            //attributes?.let {
            //    val typedArray = context?.obtainStyledAttributes(attributes, R.styleable.clap_fab, 0, 0)
            //}

            // Init Animations
            initAnimation()
        }
    }

    private fun initAnimation()
    {
        initDotsAnim()
    }

    private fun initDotsAnim()
    {
        dotsView.setColors(resources.getColor(R.color.dotsColor1), resources.getColor(R.color.dotsColor2))
        dotsView.currentProgress = 0f
        dotsView.setSize(400, 400)
    }

    private fun playActualFabAnim()
    {
        isHideAnimStopped = true

        playDotsAnimation()

        // 1. Scale Up FAB Button On Each Click
        fabScaleUpAnimation()

        // 2. Show and move count text circle from button
        // If circle is not shown on top, then move up and show circle
        if (!isCirlceAvailable)
        {
            circleShowMoveUpAnimation()
        }
        // Else, scale up the counter text
        else
        {
            circleScaleAnimation()
        }
    }

    private fun playDotsAnimation()
    {
        dotsView.currentProgress = 0f

        var dotsAnimator = ObjectAnimator.ofFloat(dotsView, DotsView.DOTS_PROGRESS, 0f, 1f)
        dotsAnimator.duration = 500
        dotsAnimator.interpolator = AccelerateDecelerateInterpolator()
        dotsAnimator.start()
    }


    private fun fabScaleUpAnimation()
    {
        fabScaleAnimation_1 = ViewAnimator
                .animate(fabDemoClap)
                .scale(1f, 1.2f)
                .duration(70)
                .thenAnimate(fabDemoClap)
                .scale(1.2f, 1.0f)
                .duration(70)
                .start()
                .onStop {
                    fabScaleAnimation_1 = null
                }
    }

    private fun circleShowMoveUpAnimation()
    {
        txtCountCircle.visibility = View.VISIBLE
        //txtCountCircle.elevation = 7f
        txtCountCircle.y = fabDemoClap.y + fabDemoClap.height/2
        txtCountCircle.alpha = 0f
        circleShowMoveUpAnimation_2 = ViewAnimator
                .animate(txtCountCircle)
                .dp().translationY(0f, -70f)
                .interpolator(DecelerateInterpolator())
                .alpha(0f, 1f)
                .duration(500)
                .onStop {
                    isCirlceAvailable = true
                    //circleShowMoveUpAnimation_2 = null
                    //circleHideMoveAnimation()
                    isHideAnimStopped = false
                    hideAnimTimer.start()
                }
                .start()
    }

    private fun circleScaleAnimation()
    {
        circleScaleAnimation_3 = ViewAnimator
                .animate(txtCountCircle)
                .scale(1f, 1.2f)
                .duration(70)
                .thenAnimate(txtCountCircle)
                .scale(1.2f, 1.0f)
                .duration(70)
                .onStop {
                    // Hide Circle Anim
                    //circleScaleAnimation_3 = null
                    //circleHideMoveAnimation()
                    isHideAnimStopped = false
                    hideAnimTimer.start()
                }
                .start()
    }

    var hideAnimTimer = object : CountDownTimer(800, 50){
        override fun onTick(millisUntilFinished: Long) {
            if (isHideAnimStopped)
                cancel()
        }

        override fun onFinish() {
            post {
                circleHideMoveAnimation()
            }
        }
    }

    private fun circleHideMoveAnimation()
    {
        circleHideMoveAnimation_4?.cancel()
        circleHideMoveAnimation_4 = ViewAnimator
                .animate(txtCountCircle)
                .alpha(1f, 0f)
                .dp().translationY( -70f, -140f)
                .duration(400)
                //.startDelay(1500)
                .onStop {
                    isCirlceAvailable = false
                    //circleShowMoveUpAnimation_2 = null
                    //circleHideMoveAnimation_4 = null
                }
                .start()
    }
}