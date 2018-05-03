package com.loic.pomplard.fiches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loic.pomplard.base.BaseFragment

class GnrFragment : BaseFragment<GnrFragmentPresenterImpl>(), GnrFragmentPresenter.View {

    fun newInstance(): GnrFragment {
        return GnrFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = GnrFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        presenter?.doSomethingWithGnr()

        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun doSomethingWithGnr() {
        Toast.makeText(activity, "gnr", Toast.LENGTH_LONG).show()
    }

}