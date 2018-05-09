package com.loic.pomplard.fiches

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl
import com.loic.pomplard.fiches.models.Fiche


class FicheFragmentPresenterImpl(val v: FicheFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), FicheFragmentPresenter, FicheFragmentPresenter.FicheListener {

    private val TAG = FicheFragmentPresenterImpl::class.java.getName()

    override fun viewReady() {
        getFiches(this)
    }

    /**
     * Retrieve the ARI Fiche.
     */
    private fun getFiches(listener: FicheFragmentPresenter.FicheListener) {

        val db = FirebaseFirestore.getInstance()
        val ariReference: DocumentReference = db.collection("ressources").document("incendie/fiches/ari")
        ariReference.get().addOnSuccessListener { document: DocumentSnapshot ->
            if (document.exists()) {
                Log.d(TAG, "DocumentSnapshot data: " + document.getData())
                val ficheAri = document.toObject(Fiche::class.java)!!
                listener.onSuccessFicheListener(ficheAri)
            } else {
                Log.d(TAG, "No such document");
                listener.onErrorFicheListener()
            }
        }
    }

    override fun onSuccessFicheListener(fiche: Fiche) {
        v.showTitle(fiche.title)
        v.showDescription(fiche.description)
        v.showImage(loadImage())
    }

    private fun loadImage(): StorageReference {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference()
        return storageReference.child("ari.png")
    }

    override fun onErrorFicheListener() {
        v.showErrorToast()
    }

}