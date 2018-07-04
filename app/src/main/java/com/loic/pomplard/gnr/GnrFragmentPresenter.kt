package com.loic.pomplard.gnr

import android.content.Context
import com.loic.pomplard.gnr.models.Gnr
import java.io.File

interface GnrFragmentPresenter {

    fun viewReady(fragment: GnrFragment, gnr: Gnr)

    fun initGnrs(context: Context): List<Gnr>

    fun getGnrPdf()

    interface View {
        fun onSuccessPermission()
        fun onDeniedPermission()
        fun downloadGnr(file: File)
    }
}