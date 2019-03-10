package com.oleg.photodocs.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.oleg.photodocs.R
import com.oleg.photodocs.pref.PrefUtils
import com.oleg.photodocs.presentation.NavigationListener
import com.oleg.photodocs.presentation.utils.inflate

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-10
 * Time: 08:57
 */

class SplashFragment: Fragment() {

    private fun getSplashScreenDuration() = if (PrefUtils.token?.isEmpty()!!)2500L else 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        scheduleSplashScreen()
        return context?.inflate(R.layout.fmt_splash)
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                findNavController().navigate(R.id.select_fragment)
            },
            splashScreenDuration
        )
    }

}


