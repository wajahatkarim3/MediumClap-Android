package com.wajahatkarim3.clapfab

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.widget.FrameLayout
import java.util.jar.Attributes

/**
 * Created by wajah on 2/7/2018.
 */
class ClapFAB : FrameLayout
{
    val TAG = ClapFAB::class.simpleName

    constructor(context: Context?) : super(context)
    {
        init(context, null)
    }

    constructor(context: Context?, attributes: AttributeSet) : super(context, attributes)
    {
        init(context, attributes)
    }

    constructor(context: Context?, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    {
        init(context, attributeSet)
    }

    fun init(context: Context?, attributes: AttributeSet?)
    {
        // Set Default Values Here


        // Check for attributes
        if (attributes != null)
        {
            //val typedArray = context?.obtainStyledAttributes(attributes, R.styleable.clap_fab, 0, 0)

        }

        //
    }



}