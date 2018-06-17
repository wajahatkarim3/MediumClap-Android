package com.wajahatkarim3.clapfab

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ViewAnimator
import java.util.jar.Attributes

/**
 * Created by wajah on 2/7/2018.
 */
class ClapFAB : FrameLayout
{
    val TAG = ClapFAB::class.simpleName

    private var clapCount = 0
    private var isCircleAvailable = false

    // Animations
    var fabScaleAnimation_1: ViewAnimator? = null
    var circleShowMoveUpAnimation_2: ViewAnimator? = null
    var circleScaleAnimation_3: ViewAnimator? = null
    var circleHideMoveAnimation_4: ViewAnimator? = null

    constructor(context: Context) : this(context, null)
    {
        init(context, null)
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    {
        init(context, attrs)
    }


    fun init(context: Context?, attributes: AttributeSet?)
    {
        // Set Default Values Here

        // Check for attributes
        //attributes?.let {
        //    val typedArray = context?.obtainStyledAttributes(attributes, R.styleable.clap_fab, 0, 0)
        //}

        // Init Animations
        initAnimation()
    }

    private fun initAnimation()
    {
        initDotsAnim()
    }

    private fun initDotsAnim()
    {

    }



}