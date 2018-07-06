package com.loic.pomplard

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.loic.pomplard.base.BaseActivity
import com.loic.pomplard.fiches.FicheFragment
import com.loic.pomplard.gnr.GnrFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : BaseActivity<MainActivityPresenterImpl>(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var mAuth: FirebaseAuth
    private val TAG = MainActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.selectedItemId = R.id.nav_gnr //Check the first item in navigation Drawer
        if (savedInstanceState == null) {
            bottom_navigation.getMenu().performIdentifierAction(R.id.nav_gnr, 0); //Launch the first fragment when starting application
        }

        //Initialisation Authentication Firebase
        mAuth = FirebaseAuth.getInstance()
        signInAnonymously()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_gnr -> {
                val fragmentManager = supportFragmentManager
                val ft = fragmentManager.beginTransaction()
                val gnrFragment = GnrFragment().newInstance()
                ft.replace(R.id.content_frame, gnrFragment)
                ft.addToBackStack(null)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()

            }
            R.id.nav_fiche -> {
                val fragmentManager = supportFragmentManager
                val ft = fragmentManager.beginTransaction()
                val ficheFragment = FicheFragment().newInstance()
                ft.replace(R.id.content_frame, ficheFragment)
                ft.addToBackStack(null)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
            }
        }

        return true
    }

    /**
     * Sign in anonymously to use secure functions in Firebase
     */
    private fun signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInAnonymously:success")
                        val user = mAuth.currentUser
                    } else {
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                    }
                }
    }
}
