package com.oleg.photodocs.presentation.select

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.oleg.photodocs.R
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.pref.PrefUtils
import com.oleg.photodocs.presentation.NavigationListener
import com.oleg.photodocs.presentation.model.Document
import com.oleg.photodocs.presentation.utils.inflate
import com.oleg.photodocs.presentation.viewmodel.DocumentViewModel
import kotlinx.android.synthetic.main.fmt_select.*
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-10
 * Time: 10:06
 */

class SelectFragment : Fragment() {
    private val mDocumentVm: DocumentViewModel by viewModel()

    private val itemClick: (Document, Int) -> Unit =
        { document, position ->

            DocumentListAdapter.selectedItem = position
            adapter.notifyDataSetChanged()

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
//            PrefUtils.documentPid = document.id
//            PrefUtils.documentName = document.name

//            val action = SelectFragmentDirections.actionToCameraFragment()
//            action.documentName = document.name
//            findNavController().navigate(R.id.camera_fragment)
        }

    private val adapter = DocumentListAdapter(itemClick)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_select)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        select_rv.adapter = adapter

        mDocumentVm.documents.observe(this@SelectFragment, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.ERROR -> {
                        Timber.e("Failed get Document's:\n ${it.message}")
                    }
                    ResourceState.SUCCESS -> {
                        adapter.submitList(it.data)
                    }
                }
            }
        })

    }


    override fun onStart() {
        super.onStart()
        mDocumentVm.getDocuments()
    }
}