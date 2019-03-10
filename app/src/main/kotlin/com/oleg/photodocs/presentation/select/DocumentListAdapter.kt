package com.oleg.photodocs.presentation.select

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oleg.photodocs.R
import com.oleg.photodocs.presentation.model.Document
import com.oleg.photodocs.presentation.utils.inflate
import kotlinx.android.synthetic.main.item_select.view.*

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-10
 * Time: 13:42
 */

class DocumentListAdapter constructor(private val itemClick: (Document) -> Unit) :
    ListAdapter<Document, DocumentListAdapter.ViewHolder>(DocumentDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentListAdapter.ViewHolder =
        ViewHolder(parent)

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_select)) {

        fun bind(item: Document) {
            itemView.name_tv.text = item.name
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }

}


private class DocumentDiffCallback : DiffUtil.ItemCallback<Document>() {
    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(old: Document, new: Document): Boolean =
        old.name == new.name
}