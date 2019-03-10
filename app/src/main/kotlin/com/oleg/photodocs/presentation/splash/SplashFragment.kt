package com.oleg.photodocs.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.oleg.photodocs.R
import com.oleg.photodocs.presentation.NavigationListener
import com.oleg.photodocs.presentation.utils.inflate

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-10
 * Time: 08:57
 */

class SplashFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_splash)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            context?.let {
                findNavController().navigate(R.id.select_fragment)
            }
        }, 2500)
    }


}


