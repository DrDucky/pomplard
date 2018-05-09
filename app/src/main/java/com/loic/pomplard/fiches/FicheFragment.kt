package com.loic.pomplard.fiches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.loic.pomplard.base.BaseFragment

class FicheFragment : BaseFragment<FicheFragmentPresenterImpl>(), FicheFragmentPresenter.View {

    fun newInstance(): FicheFragment {
        return FicheFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = FicheFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        presenter?.viewReady()

        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun showToast() {
        Toast.makeText(activity, "lala", Toast.LENGTH_LONG).show()
    }

}