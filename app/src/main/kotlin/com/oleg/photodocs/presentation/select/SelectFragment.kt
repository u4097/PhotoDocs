package com.oleg.photodocs.presentation.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oleg.photodocs.R
import com.oleg.photodocs.presentation.utils.inflate

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-10
 * Time: 10:06
 */

class SelectFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_select)

    }
}