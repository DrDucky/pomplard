package com.loic.pomplard.gnr

import android.content.Context
import java.io.File

interface GnrFragmentPresenter {

    fun checkPermission(fragment: GnrFragment)

    fun doSomething()

    interface View {
        fun onSuccessPermission()
        fun ononDeniedPermission()
        fun doSomethingWithGnr(file: File)
    }
}