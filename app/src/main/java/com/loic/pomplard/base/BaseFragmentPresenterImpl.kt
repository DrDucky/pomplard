package com.loic.pomplard.base

import android.content.Context
import java.lang.ref.WeakReference

/**
 * Created by lmecatti on 25/04/2018.
 */
open class BaseFragmentPresenterImpl<V, S> : BaseFragmentPresenter {

    protected lateinit var context: Context;
    protected var view: WeakReference<V>? = null

    protected fun BaseFragmentPresenterImpl(context: Context) {
        this.context = context
    }

    override fun viewDestroyed() {
        view?.clear()
    }

}