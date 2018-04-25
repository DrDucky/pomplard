package com.loic.pomplard

import android.os.Bundle
import android.widget.Toast
import com.loic.pomplard.base.BaseActivity

class MainActivity : BaseActivity<MainActivityPresenterImpl>(), MainActivityPresenter.View{

    val presenter: MainActivityPresenterImpl = MainActivityPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.doSomething()
    }

    override fun doSomething() {
        Toast.makeText(this, "Lala", Toast.LENGTH_LONG).show()
    }
}
