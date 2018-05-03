package com.loic.pomplard.fiches

import android.widget.Toast
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl


class FicheFragmentPresenterImpl(val v: FicheFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), FicheFragmentPresenter.View {

    override fun doSomethingWithFiches() {
        v.doSomethingWithFiches()

    }

}