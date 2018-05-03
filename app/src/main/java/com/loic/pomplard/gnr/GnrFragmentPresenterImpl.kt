package com.loic.pomplard.fiches

import android.widget.Toast
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl


class GnrFragmentPresenterImpl(val v: GnrFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), GnrFragmentPresenter.View {

    override fun doSomethingWithGnr() {
        v.doSomethingWithGnr()

    }

}