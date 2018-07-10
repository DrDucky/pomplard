package com.loic.pomplard.gnr

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.net.Uri
import android.os.Build
import android.os.Bundle
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

    var gnrList = mutableListOf<Gnr>()
    lateinit var gnrSelected:Gnr
    lateinit var adapter:GnrAdapter
    var filtersCategories: String = ""

    fun newInstance(): GnrFragment {
        return GnrFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = GnrFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_gnr, container, false)

        presenter?.viewReady(this)

        gnrList = presenter!!.initGnrs(context!!)

        adapter = GnrAdapter(gnrList, this)

        rootView.rv_gnr.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
        rootView.rv_gnr.adapter = adapter

        rootView.chips_category_specialites.setOnCheckedChangeListener { button, checked ->
            presenter!!.filterCategories(checked, filtersCategories, FilesConstants.SPECIALITES_CATEGORY)
        }

        rootView.chips_category_operationnel.setOnCheckedChangeListener { button, checked ->
            presenter!!.filterCategories(checked, filtersCategories, FilesConstants.OPERATIONNEL_CATEGORY)
        }

        return rootView

    }

    override fun filterCategories(pCategory: String){
        this.filtersCategories = pCategory
        adapter.filter.filter(pCategory)
    }

    override fun onSuccessPermission() {
        presenter!!.getGnrPdf(gnrSelected)
    }

    override fun onDeniedPermission() {
        Toast.makeText(activity, getString(R.string.permission_denied), Toast.LENGTH_LONG).show()
    }

    override fun downloadGnr(file:File) {
        Toast.makeText(activity, "gnr : " + file.name, Toast.LENGTH_LONG).show()

        val intent =  Intent(Intent.ACTION_VIEW)

        if (Build.VERSION.SDK_INT < 24) {
            intent.setDataAndType(Uri.fromFile(file), "application/pdf")
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }else {
            val pdfUri = FileProvider.getUriForFile(activity!!.applicationContext, activity!!.packageName, file)
            intent.setAction(Intent.ACTION_VIEW)
            intent.setData(pdfUri)
            intent.setDataAndType(pdfUri, "application/pdf")
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION)
        }
        startActivity(intent)
    }

    override fun onItemClickListener(gnr: Gnr) {
        this.gnrSelected = gnr
        presenter!!.checkPermission(this)
    }

}