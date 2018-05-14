package com.loic.pomplard.gnr

import android.Manifest
import android.os.Environment
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl
import ru.alexbykov.nopermission.PermissionHelper
import java.io.File


class GnrFragmentPresenterImpl(val v: GnrFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), GnrFragmentPresenter {

    private val TAG = GnrFragmentPresenterImpl::class.java.getName()

    override fun viewReady(fragment: GnrFragment) {
        checkPermission(fragment)
    }

    fun checkPermission(fragment: GnrFragment) {
        val permissionHelper = PermissionHelper(fragment)

        permissionHelper.check(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onSuccess(this::onSuccess)
                .onDenied(this::onDenied)
                .run();
    }

    private fun onSuccess(){
        v.onSuccessPermission()
    }

    private fun onDenied(){
        v.onDeniedPermission()
    }

    override fun getLspccPdf() {

        val internalFile = File(Environment.getExternalStorageDirectory().path + File.separator + "Plompard" + File.separator, "gnrLSPCC.pdf")

        if(!internalFile.exists()) {
            internalFile.parentFile.mkdirs()
            internalFile.createNewFile()
        }
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference()
        val pdfFile = storageReference.child("GNR_lot_de_sauvetage_et_de_protection_contre_les_chutes.pdf")
        pdfFile.getFile(internalFile).addOnSuccessListener { taskSnapshot: FileDownloadTask.TaskSnapshot? -> v.doSomethingWithGnr(internalFile) }
    }
}