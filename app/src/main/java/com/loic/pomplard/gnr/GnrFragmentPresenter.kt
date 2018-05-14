package com.loic.pomplard.gnr

import android.content.Context
import java.io.File

interface GnrFragmentPresenter {

    fun viewReady(fragment: GnrFragment)

    fun getLspccPdf()

    interface View {
        fun onSuccessPermission()
        fun onDeniedPermission()
        fun doSomethingWithGnr(file: File)
    }
}