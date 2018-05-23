package com.loic.pomplard.gnr

import android.Manifest
import android.os.Environment
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.base.BaseFragmentPresenterImpl
import com.loic.pomplard.constants.FilesConstants
import com.loic.pomplard.gnr.models.Gnr
import ru.alexbykov.nopermission.PermissionHelper
import java.io.File


class GnrFragmentPresenterImpl(val v: GnrFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), GnrFragmentPresenter {

    private val TAG = GnrFragmentPresenterImpl::class.java.getName()
    lateinit var gnrSelected:Gnr

    override fun viewReady(fragment: GnrFragment, gnr:Gnr) {
        this.gnrSelected = gnr
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

    override fun getGnrPdf() {

        val localGnr = File(Environment.getExternalStorageDirectory().path + File.separator + FilesConstants.APP_LOCAL_DIRECTORY + File.separator, gnrSelected.srcPdf + FilesConstants.PDF_EXTENSION)

        if(!localGnr.exists()) {
            localGnr.parentFile.mkdirs()
            localGnr.createNewFile()
        }
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference()
        val pdfFile = storageReference.child(gnrSelected.srcPdf + FilesConstants.PDF_EXTENSION)
        pdfFile.getFile(localGnr).addOnSuccessListener { taskSnapshot: FileDownloadTask.TaskSnapshot? -> v.downloadGnr(localGnr) }
    }
}