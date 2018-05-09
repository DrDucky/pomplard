package com.loic.pomplard.gnr

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loic.pomplard.R
import com.loic.pomplard.base.BaseFragment
import java.io.File

class GnrFragment : BaseFragment<GnrFragmentPresenterImpl>(), GnrFragmentPresenter.View {

    fun newInstance(): GnrFragment {
        return GnrFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = GnrFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_gnr, container, false)

        //DO CURRENTLY NOTHING.
        //WORK IN PROGRESSS
        //presenter?.checkPermission(this)

        return rootView

    }

    override fun doSomethingWithGnr(file:File) {
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
        presenter?.doSomething()
    }

    override fun ononDeniedPermission() {
        Toast.makeText(activity, "Permission needed ! ", Toast.LENGTH_LONG).show()
    }

}