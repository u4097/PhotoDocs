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
import androidx.lifecycle.ViewModelProviders
import com.oleg.photodocs.AppConfiguration.getRootViewContainerFor
import com.oleg.photodocs.AppConfiguration.riseAndShine
import com.oleg.photodocs.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.games_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get root container to put our app's UI in. For a debug build this will have our debug drawer.
        // For a release build this will be the Activity's root container.
        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)

        riseAndShine(this)

        // Wire up navigation drawer to open on toolbar button clicks.
        val toolbar: Toolbar = findViewById(R.id.home_toolbar)

        val viewAnimator: ViewAnimator = findViewById(R.id.games_viewAnimator)
        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        // Observe ViewModel state and change UI accordingly.
        viewModel.states.observe(this, Observer { state ->
            when (state) {
                MainViewModel.State.Idle, MainViewModel.State.Loading -> viewAnimator.displayedChild = 0

                MainViewModel.State.Error -> {
                    viewAnimator.displayedChild = 1
                    val errorImageView: ImageView = findViewById(R.id.games_error_image)
                    Picasso.get().load(R.drawable.gfx_dead_link_small).into(errorImageView)
                }

                is MainViewModel.State.Success -> {

                    token_tv.text = state.response.token
                    viewAnimator.displayedChild = 2
                }
            }
        })


        // Put refresh button in toolbar menu and have it refresh the games list.
        toolbar.inflateMenu(R.menu.home)
        toolbar.setOnMenuItemClickListener {
            viewModel.login()
            return@setOnMenuItemClickListener true
        }

    }

    override fun onStart() {
        super.onStart()

        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.refreshIfNecessary()
    }


}
