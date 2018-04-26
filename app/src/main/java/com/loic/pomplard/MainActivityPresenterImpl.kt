package com.loic.pomplard

import com.loic.pomplard.base.BaseActivityPresenterImpl

/**
 * Created by lmecatti on 25/04/2018.
 */
public class MainActivityPresenterImpl(val v: MainActivityPresenter.View) : BaseActivityPresenterImpl<MainActivityPresenter.View, Void>(), MainActivityPresenter.View {

    override fun doSomething() {
        v.doSomething()
    }

}