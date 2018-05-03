package com.loic.pomplard.base

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by lmecatti on 25/04/2018.
 */
abstract class BaseFragment<E : BaseFragmentPresenter>() : Fragment() {

    protected var presenter: E? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}