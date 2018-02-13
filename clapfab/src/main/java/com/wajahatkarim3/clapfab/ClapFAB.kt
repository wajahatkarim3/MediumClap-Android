package com.wajahatkarim3.clapfab

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.widget.FrameLayout
import java.util.jar.Attributes

/**
 * Created by wajah on 2/7/2018.
 */
class ClapFAB
    /*@JvmOverloads constructor
    (
    context: Context?,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0)*/
    : FrameLayout
{
    val TAG = ClapFAB::class.simpleName


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
        if (attributes != null)
        {
            //val typedArray = context?.obtainStyledAttributes(attributes, R.styleable.clap_fab, 0, 0)

        }

        //
    }



}