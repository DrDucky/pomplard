package com.loic.pomplard.fiches

import com.google.firebase.storage.StorageReference
import com.loic.pomplard.fiches.models.Fiche

interface FicheFragmentPresenter {

    fun viewReady()

    interface View {
        fun showTitle(titleText:String)
        fun showDescription(descriptionText:String)
        fun showImage(storageReference: StorageReference)
        fun showErrorToast()
    }

    interface FicheListener{
        fun onSuccessFicheListener(fiche: Fiche)
        fun onErrorFicheListener()
    }
}