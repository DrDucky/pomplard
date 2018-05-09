package com.loic.pomplard.fiches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import com.loic.pomplard.GlideApp
import com.loic.pomplard.PomplardGlideModule
import com.loic.pomplard.R
import com.loic.pomplard.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_fiche.*

class FicheFragment : BaseFragment<FicheFragmentPresenterImpl>(), FicheFragmentPresenter.View {

    fun newInstance(): FicheFragment {
        return FicheFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = FicheFragmentPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fiche, container, false)

        presenter?.viewReady()

        return rootView

    }

    override fun showTitle(titleText:String) {
        title.setText(titleText)
    }

    override fun showDescription(descriptionText:String) {
        description.setText(descriptionText)
    }

    override fun showImage(storageReference: StorageReference) {
        GlideApp.with(this)
                .load(storageReference)
                .into(image)
    }


    override fun showErrorToast() {
        Toast.makeText(activity, "Error! ", Toast.LENGTH_LONG).show()
    }
}