package com.loic.pomplard.fiches

import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl


class FicheFragmentPresenterImpl(val v: FicheFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), FicheFragmentPresenter {

    override fun viewReady() {
        v.showToast()

    }

    fun getFiches(category: String){
        val db: FirebaseFirestore.
    }

}