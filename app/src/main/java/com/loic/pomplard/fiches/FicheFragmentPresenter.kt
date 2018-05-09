package com.loic.pomplard.fiches

interface FicheFragmentPresenter {

    fun viewReady()

    interface View {
        fun showToast()
    }
}