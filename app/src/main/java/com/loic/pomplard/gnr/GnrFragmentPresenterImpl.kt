package com.loic.pomplard.gnr

import android.Manifest
import android.content.Context
import android.os.Environment
import android.support.v4.content.ContextCompat
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.loic.pomplard.MainActivityPresenter
import com.loic.pomplard.R
import com.loic.pomplard.base.BaseFragmentPresenterImpl
import com.loic.pomplard.constants.FilesConstants
import com.loic.pomplard.gnr.models.Gnr
import com.loic.pomplard.utils.DataUtils
import ru.alexbykov.nopermission.PermissionHelper
import java.io.File


class GnrFragmentPresenterImpl(val v: GnrFragmentPresenter.View) : BaseFragmentPresenterImpl<MainActivityPresenter.View, Void>(), GnrFragmentPresenter {

    private val TAG = GnrFragmentPresenterImpl::class.java.getName()
    lateinit var fragment:GnrFragment

    override fun viewReady(fragment: GnrFragment) {
        this.fragment = fragment
    }

    override fun initGnrs( context: Context): MutableList<Gnr> {
        val gnrList = mutableListOf<Gnr>()

        val gnrLSPCC = Gnr(FilesConstants.LSPCC_TITLE,
                fragment.getString(R.string.lspcc_description),
                ContextCompat.getDrawable(context, R.drawable.lspcc_thumbnail)!!,
                FilesConstants.LSPCC_NAME)

        val gnrARI = Gnr(FilesConstants.ARI_TITLE,
                fragment.getString(R.string.ari_description),
                ContextCompat.getDrawable(context, R.drawable.ari_thumbnail)!!,
                FilesConstants.ARI_NAME
        )

        val gnrEFEGE = Gnr(FilesConstants.EF_EGE_TITLE,
                fragment.getString(R.string.ef_ege_description),
                ContextCompat.getDrawable(context, R.drawable.ef_ege_thumbnail)!!,
                FilesConstants.EF_EGE_NAME
        )

        val gnrEtablissements = Gnr(FilesConstants.ETABLISSEMENTS_TITLE,
                fragment.getString(R.string.etablissements_description),
                ContextCompat.getDrawable(context, R.drawable.etablissements_thumbnail)!!,
                FilesConstants.ETABLISSEMENTS_NAME
        )

        gnrList += gnrLSPCC
        gnrList += gnrARI
        gnrList += gnrEFEGE
        gnrList += gnrEtablissements

        return gnrList
    }

    /**
     * Check if the {@see Manifest.permission.WRITE_EXTERNAL_STORAGE} has been already accepted|refused.
     */
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

    /**
     * Retrieve the PDF file from Internet (Firebase) or locally.
     */
    override fun getGnrPdf(gnrSelected : Gnr) {

        val localGnr = DataUtils.getLocalFile(gnrSelected.srcPdf, FilesConstants.PDF_EXTENSION)

        if(!localGnr.exists()) {
            /**
             * Download the PDF file from Firebase
             */
            localGnr.parentFile.mkdirs()
            localGnr.createNewFile()
            val storage = FirebaseStorage.getInstance()
            val storageReference = storage.getReference()
            val pdfFile = storageReference.child(gnrSelected.srcPdf + FilesConstants.PDF_EXTENSION)
            pdfFile.getFile(localGnr).addOnSuccessListener { taskSnapshot: FileDownloadTask.TaskSnapshot? -> v.downloadGnr(localGnr) }
        } else {
            /**
             * Launch the view to open the existing PDF file
             */
            v.downloadGnr(localGnr)
        }

    }
}