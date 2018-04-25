package com.loic.pomplarg.base

import android.content.Context
import java.lang.ref.WeakReference

/**
 * Created by lmecatti on 25/04/2018.
 */
public open class BaseActivityPresenterImpl<V, S> : BaseActivityPresenter {

    protected lateinit var context: Context;
    protected var view: WeakReference<V>? = null
    protected var presenterState: S? = null

    protected fun BaseActivityPresenterImpl(context: Context) {
        this.context = context
    }

    override fun viewDestroyed() {
        view?.clear()
    }

}