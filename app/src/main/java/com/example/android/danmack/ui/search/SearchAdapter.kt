package com.example.android.danmack.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.danmack.databinding.SearchListItemBinding
import com.example.android.danmack.model.searchmodel.TermData
import com.example.android.danmack.model.songmodel.Track

class SearchAdapter (val clickListener : SearchClickListener) : ListAdapter<TermData, SearchAdapter.SearchViewHolder> (SearchDiffCallback()) {



    class SearchViewHolder (val ui : SearchListItemBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind(item : TermData, clickListener : SearchClickListener) {
            ui.term = item
            ui.searchTitle.text = item.term
            ui.clickListener = clickListener
            ui.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup) : SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val ui = SearchListItemBinding.inflate(layoutInflater)

                return SearchViewHolder(ui)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = holder.bind(getItem(position), clickListener)


    class SearchDiffCallback : DiffUtil.ItemCallback<TermData>() {
        override fun areItemsTheSame(oldItem: TermData, newItem: TermData) = oldItem.term == newItem.term

        override fun areContentsTheSame(oldItem: TermData, newItem: TermData) = oldItem == newItem

    }


    class SearchClickListener(val clickListener : (term : TermData) -> Unit) {
        fun onClick (term : TermData) = clickListener(term)
    }

}