package com.loic.pomplard.gnr

import android.content.Context
import com.loic.pomplard.gnr.models.Gnr
import java.io.File

interface GnrFragmentPresenter {

    fun viewReady(fragment: GnrFragment)

    fun initGnrs(context: Context): List<Gnr>

    fun getGnrPdf(gnrSelected : Gnr)

    interface View {
        fun onSuccessPermission()
        fun onDeniedPermission()
        fun downloadGnr(file: File)
    }
}