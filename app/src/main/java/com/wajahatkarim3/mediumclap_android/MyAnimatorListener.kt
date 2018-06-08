package com.wajahatkarim3.mediumclap_android

import android.animation.Animator

class MyAnimatorListener(initMethod: MyAnimatorListener.() -> Unit) : Animator.AnimatorListener {

    private var _endListener: (animation: Animator?) -> Unit = { _ -> }
    private var _cancelListener: (animation: Animator?) -> Unit = { _ -> }
    private var _repeatListener: (animation: Animator?) -> Unit = { _ -> }
    private var _startListener: (animation: Animator?) -> Unit = { _ -> }

    override fun onAnimationEnd(animation: Animator?) {
        _endListener?.invoke(animation)
    }

    override fun onAnimationCancel(animation: Animator?) {

    }

    override fun onAnimationRepeat(animation: Animator?) {

    }

    override fun onAnimationStart(animation: Animator?) {

    }

    fun setOnAnimationEnd(method: (animation: Animator?) -> Unit)
    {
        _endListener = method
    }

    init {
        initMethod()
    }
}