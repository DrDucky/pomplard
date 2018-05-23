package com.loic.pomplard.gnr

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loic.pomplard.R
import com.loic.pomplard.base.BaseFragment
import com.loic.pomplard.constants.FilesConstants
import com.loic.pomplard.gnr.models.Gnr
import java.io.File
import kotlinx.android.synthetic.main.fragment_gnr.view.*


class GnrFragment : BaseFragment<GnrFragmentPresenterImpl>(), GnrFragmentPresenter.View, GnrListener {

    val gnrList = mutableListOf<Gnr>()

    fun newInstance(): GnrFragment {
        return GnrFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = GnrFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_gnr, container, false)
        val gnrLSPCC = Gnr("GNR LSPCC",
                ContextCompat.getDrawable(this.context!!, R.drawable.lspcc_thumbnail)!!,
                FilesConstants.LSPCC_NAME)

        val gnrARI = Gnr("GNR ARI",
                ContextCompat.getDrawable(this.context!!, R.drawable.ari_thumbnail)!!,
                FilesConstants.ARI_NAME)

        gnrList += gnrLSPCC
        gnrList += gnrARI

        rootView.rv_gnr.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rootView.rv_gnr.adapter = GnrAdapter(gnrList, this)

        return rootView

    }

    override fun downloadGnr(file:File) {
        Toast.makeText(activity, "gnr : " + file.name, Toast.LENGTH_LONG).show()

        val intent =  Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT < 24) {
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }else {
            val pdfUri = FileProvider.getUriForFile(activity!!.applicationContext, activity!!.packageName, file)
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(pdfUri);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivity(intent);
    }

    override fun onSuccessPermission() {
        presenter?.getGnrPdf()
    }

    override fun onDeniedPermission() {
        Toast.makeText(activity, getString(R.string.permission_denied), Toast.LENGTH_LONG).show()
    }

    override fun onItemClickListener(gnr: Gnr) {
        presenter?.viewReady(this, gnr)    }

}