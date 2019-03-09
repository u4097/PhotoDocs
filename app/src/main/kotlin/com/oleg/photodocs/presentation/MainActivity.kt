package com.oleg.photodocs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.games_list.*
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val mLoginVm: LoginViewModel by viewModel()
    private val mDocumentVm: DocumentViewModel by viewModel()
    private val mSuitVm: SuitViewModel by viewModel()
    private val mBackgroundVm: BackgroundViewModel by viewModel()

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

        val viewAnimator: ViewAnimator = findViewById(R.id.games_viewAnimator)


        // Observe ViewModel state and change UI accordingly.
        mLoginVm.loginEventResponse.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> viewAnimator.displayedChild = 0
                    ResourceState.ERROR -> {
                        viewAnimator.displayedChild = 1
                        val errorImageView: ImageView = findViewById(R.id.games_error_image)
                        Picasso.get().load(R.drawable.gfx_dead_link_small).into(errorImageView)
                    }
                    ResourceState.SUCCESS -> {
//                        mDocumentVm.getDocuments(refresh = false)
                        PrefUtils.token = it.data?.token
                        token_tv.text = it.data?.token
                        viewAnimator.displayedChild = 2
                    }
                }
            }
        })

        // Document observer
        mDocumentVm.documents.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> viewAnimator.displayedChild = 0
                    ResourceState.ERROR -> {
                        viewAnimator.displayedChild = 1
                        val errorImageView: ImageView = findViewById(R.id.games_error_image)
                        Picasso.get().load(R.drawable.gfx_dead_link_small).into(errorImageView)
                    }
                    ResourceState.SUCCESS -> {
                        Timber.d("Document list size: ${it.data?.size}")
                        token_tv.text = it.data?.toString()
                        viewAnimator.displayedChild = 2

                    }
                }
            }
        })

        // Suit observer
        mSuitVm.suitsData.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> viewAnimator.displayedChild = 0
                    ResourceState.ERROR -> {
                        viewAnimator.displayedChild = 1
                        val errorImageView: ImageView = findViewById(R.id.games_error_image)
                        Picasso.get().load(R.drawable.gfx_dead_link_small).into(errorImageView)
                    }
                    ResourceState.SUCCESS -> {
                        Timber.d("Suit list size: ${it.data?.size}")
                        token_tv.text = it.data?.toString()
                        viewAnimator.displayedChild = 2

                    }
                }
            }
        })

        // Background observer
        mBackgroundVm.backgroundsData.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> viewAnimator.displayedChild = 0
                    ResourceState.ERROR -> {
                        viewAnimator.displayedChild = 1
                        val errorImageView: ImageView = findViewById(R.id.games_error_image)
                        Picasso.get().load(R.drawable.gfx_dead_link_small).into(errorImageView)
                    }
                    ResourceState.SUCCESS -> {
                        Timber.d("Background list size: ${it.data?.size}")
                        token_tv.text = it.data?.toString()
                        viewAnimator.displayedChild = 2

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
