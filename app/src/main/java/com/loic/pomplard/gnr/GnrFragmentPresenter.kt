package com.loic.pomplard.gnr

import android.content.Context
import com.loic.pomplard.gnr.models.Gnr
import java.io.File

interface GnrFragmentPresenter {

    fun viewReady(fragment: GnrFragment)

    fun initGnrs(context: Context): List<Gnr>

    fun getGnrPdf(gnrSelected : Gnr)

    /**
     * filter on all GNRs with the specified category (ex: "Specialités" or "Operationnel")
     * By default, all GNR are displays
     */
    fun filterCategories(checked: Boolean, filtersCategories: String, categorie: String)

    interface View {
        fun onSuccessPermission()
        fun onDeniedPermission()
        fun filterCategories(pCategory: String)
        fun downloadGnr(file: File)
    }
}