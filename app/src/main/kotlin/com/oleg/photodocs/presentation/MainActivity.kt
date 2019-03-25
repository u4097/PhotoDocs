package com.oleg.photodocs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.oleg.photodocs.AppConfiguration.getRootViewContainerFor
import com.oleg.photodocs.AppConfiguration.riseAndShine
import com.oleg.photodocs.R
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.di.loadAppModules
import com.oleg.photodocs.pref.PrefUtils
import com.oleg.photodocs.presentation.model.login.Login
import com.oleg.photodocs.presentation.viewmodel.BackgroundViewModel
import com.oleg.photodocs.presentation.viewmodel.DocumentViewModel
import com.oleg.photodocs.presentation.viewmodel.LoginViewModel
import com.oleg.photodocs.presentation.viewmodel.SuitViewModel
import org.koin.androidx.viewmodel.ext.viewModel

interface ToolbarListener {
    fun updateTitle(title: String)
    fun hideToolbar()
}

interface NavigationListener {
    fun navigate(action: NavDirections)
    var mNavController: NavController
}


class MainActivity : AppCompatActivity(),
    NavigationListener,
    ToolbarListener {

    override fun updateTitle(title: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideToolbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigate(action: NavDirections) {
        Navigation.findNavController(this, R.id.host_fragment).navigate(action)
    }


    private val mLoginVm: LoginViewModel by viewModel()
    private val mDocumentVm: DocumentViewModel by viewModel()
    private val mSuitVm: SuitViewModel by viewModel()
    private val mBackgroundVm: BackgroundViewModel by viewModel()

    override lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Get root container to put our app's UI in. For a debug build this will have our debug drawer.
        // For a release build this will be the Activity's root container.
        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)


        // Koin  DI init
        loadAppModules()

        // Wake up activity in devices on run
        riseAndShine(this)

        // Wire up navigation drawer to open on toolbar button clicks.
        val toolbar: Toolbar = findViewById(R.id.home_toolbar)

//        Navigation
        mNavController = Navigation.findNavController(this, R.id.host_fragment)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splash_fragment -> {
                    supportActionBar?.hide()
                }
            }
        }


        mLoginVm.loginEventResponse.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
                        PrefUtils.token = it.data?.token
                    }
                }
            }
        })

        // Put refresh button in toolbar menu and have it refresh the games list.
        toolbar.inflateMenu(R.menu.home)
        toolbar.setOnMenuItemClickListener {
            //            mSuitVm.getSuits(refresh = false)
            mBackgroundVm.getBackgrounds(refresh = false)
            return@setOnMenuItemClickListener true
        }

    }

    override fun onStart() {
        super.onStart()
        mLoginVm.login(Login(login = "Admin", password = "admin2018"))
    }

}
