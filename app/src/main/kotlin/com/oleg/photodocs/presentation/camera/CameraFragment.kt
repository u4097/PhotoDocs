package com.oleg.photodocs.presentation.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oleg.photodocs.presentation.utils.inflate
import android.content.Intent
import com.oleg.photodocs.R


/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-10
 * Time: 15:11
 */

class CameraFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_camera)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }
}