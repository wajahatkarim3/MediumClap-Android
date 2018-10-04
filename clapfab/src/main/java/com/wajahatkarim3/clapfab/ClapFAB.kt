package com.wajahatkarim3.clapfab

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v4.widget.ImageViewCompat
import android.support.v7.content.res.AppCompatResources
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator

/**
 * Created by Wajahat Karim on 2/7/2018.
 * @author Wajahat Karim
 */
class ClapFAB
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
: RelativeLayout(context, attrs, defStyleAttr)
{
    private val TAG = ClapFAB::class.java.simpleName

    // Data Values
    private var clapCount = 0
    private var isCirlceAvailable = false
    private var isHideAnimStopped = false
    private var hidingStarted = false
    private var formatClapCount = true;

    // Animations
    private var fabScaleAnimation_1: ViewAnimator? = null
    private var circleShowMoveUpAnimation_2: ViewAnimator? = null
    private var circleScaleAnimation_3: ViewAnimator? = null
    private var circleHideMoveAnimation_4: ViewAnimator? = null

    // Views
    private lateinit var txtCountCircle: TextView
    private lateinit var dotsView: DotsView
    private lateinit var fabDemoClap: FloatingActionButton

    /**
     * Maximum Claps Count. Can't be less than 1
     */
    var maxCount: Int = 50
    set(value) {
        if (value < 1)
            field = 1
        field = value
    }

    /**
     * Default Icon Resource ID
     */
    var defaultIconResId: Int = R.drawable.ic_clap_hands_outline

    /**
     * Filled Icon Resource ID
     */
    var filledIconResId: Int = R.drawable.ic_clap_hands_filled

    /**
     * Default Icon Color Resource
     */
    var defaultIconColorRes = R.color.colorClapIcon

    /**
     * Filled Icon Color Resource
     */
    var filledIconColorRes = R.color.colorClapIcon

    /**
     * Count Circle Background Color Resource
     */
    var countCircleColorRes = R.color.colorClapIcon

    /**
     * Count Circle Text Color Resource
     */
    var countTextColorRes = R.color.white_color

    /**
     * Dots One Color Resource
     */
    var dots1ColorRes = R.color.dotsColor1

    /**
     * Dots Two Color Resource
     */
    var dots2ColorRes = R.color.dotsColor2

    /**
     * The Clap Listener
     */
    var clapListener: OnClapListener? = null

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attributes: AttributeSet?)
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
                if (clapCount > 0)
                {
                    fabDemoClap.setImageDrawable(getDrawable(filledIconResId))
                    ImageViewCompat.setImageTintList(fabDemoClap, ColorStateList.valueOf(ContextCompat.getColor(context, filledIconColorRes)))
                }

                if (clapCount > maxCount)
                {
                    clapCount = maxCount
                    clapListener?.onFabClapped(this, clapCount, true)
                    return@setOnClickListener
                }

                clapListener?.onFabClapped(this, clapCount, false)
                if(formatClapCount){
                    txtCountCircle.text = NumberUtil.format(clapCount)
                }else{
                    txtCountCircle.text = "$clapCount"
                }

                playActualFabAnim()
            }

            // Set Default Values Here
            maxCount = 50
            defaultIconResId = R.drawable.ic_clap_hands_outline
            filledIconResId = R.drawable.ic_clap_hands_filled
            defaultIconColorRes = R.color.colorClapIcon
            filledIconColorRes = R.color.colorClapIcon
            countCircleColorRes = R.color.colorClapIcon
            countTextColorRes = R.color.white_color

            // Check for attributes
            val typedArray = context.obtainStyledAttributes(attributes, R.styleable.clap_fab, 0, 0)
            typedArray.apply {
                maxCount = getInt(R.styleable.clap_fab_cf_max_clap_count, 50)
                clapCount = getInt(R.styleable.clap_fab_cf_clap_count, 0)
                formatClapCount = getBoolean(R.styleable.clap_fab_cf_clap_count, true)
                defaultIconResId = getResourceId(R.styleable.clap_fab_cf_default_icon, R.drawable.ic_clap_hands_outline)
                filledIconResId = getResourceId(R.styleable.clap_fab_cf_filled_icon, R.drawable.ic_clap_hands_filled)
                defaultIconColorRes = getResourceId(R.styleable.clap_fab_cf_default_icon_color, R.color.colorClapIcon)
                filledIconColorRes = getResourceId(R.styleable.clap_fab_cf_filled_icon_color, R.color.colorClapIcon)
                countCircleColorRes = getResourceId(R.styleable.clap_fab_cf_count_circle_color, R.color.colorClapIcon)
                countTextColorRes = getResourceId(R.styleable.clap_fab_cf_count_text_color, R.color.white_color)
                dots1ColorRes = getResourceId(R.styleable.clap_fab_cf_dots_1_color, R.color.dotsColor1)
                dots2ColorRes = getResourceId(R.styleable.clap_fab_cf_dots_2_color, R.color.dotsColor2)
            }

            // Apply Attributes
            applyAttributes()

            // Init Animations
            initAnimation()

            typedArray?.recycle()
        }
    }

    private fun applyAttributes()
    {
        fabDemoClap.setImageDrawable(getDrawable(defaultIconResId))
        ImageViewCompat.setImageTintList(fabDemoClap, ColorStateList.valueOf(ContextCompat.getColor(context, defaultIconColorRes)))
        txtCountCircle.setTextColor(ContextCompat.getColor(context, countTextColorRes))
        //txtCountCircle.setBackgroundColor(context.resources.getColor(countCircleColorRes))

        // Circle Count
        var shapeDrawable = getDrawable(R.drawable.circle_shape_background)
        var ovalShape = shapeDrawable
        ovalShape.setColorFilter(ContextCompat.getColor(context, countCircleColorRes), PorterDuff.Mode.SRC_ATOP)
        txtCountCircle.background = shapeDrawable
        txtCountCircle.setTextColor(ContextCompat.getColor(context, countTextColorRes))
        txtCountCircle.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, countTextColorRes)))

    }

    private fun initAnimation()
    {
        initDotsAnim()
    }

    private fun initDotsAnim()
    {
        dotsView.setColors(ContextCompat.getColor(context, dots1ColorRes), ContextCompat.getColor(context, dots2ColorRes))
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
        if (circleShowMoveUpAnimation_2 != null) return

        txtCountCircle.visibility = View.VISIBLE
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
                    circleShowMoveUpAnimation_2 = null
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

    private var hideAnimTimer = object : CountDownTimer(800, 50){
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
        if (hidingStarted) return
        circleHideMoveAnimation_4?.cancel()
        circleHideMoveAnimation_4 = ViewAnimator
                .animate(txtCountCircle)
                .alpha(1f, 0f)
                .dp().translationY( -70f, -140f)
                .duration(400)
                //.startDelay(1500)
                .onStart {
                    hidingStarted = true
                }
                .onStop {
                    hidingStarted = false
                    isCirlceAvailable = false
                    //circleShowMoveUpAnimation_2 = null
                    //circleHideMoveAnimation_4 = null
                }
                .start()
    }

    /**
     * Set Default clap count
     *
     * @param count Default clap count. It can be greater than {@link maxCount}
     */
    fun setClapCount(count:Int){
        if(clapCount<=maxCount){
            clapCount = count
        }
    }

    interface OnClapListener {
        fun onFabClapped(clapFab: ClapFAB, count: Int, isMaxReached: Boolean)
    }

    /**
     * Get Drawable object from resource Id, irrespective of vector or image resource.
     *
     * @param resId Drawable resource Id
     * @return Drawable object from resource Id
     */
    private fun getDrawable(resId: Int): Drawable {
        return AppCompatResources.getDrawable(context, resId)!!
    }

}