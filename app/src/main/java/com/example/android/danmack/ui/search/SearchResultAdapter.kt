package com.example.android.danmack.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.android.danmack.R
import com.example.android.danmack.databinding.SearchListItemBinding
import com.example.android.danmack.databinding.SearchResultListItemBinding
import com.example.android.danmack.model.searchmodel.SearchTrackData
import com.example.android.danmack.model.searchmodel.TermData





class SearchResultAdapter (val clickListener : SearchClickListener) : ListAdapter<SearchTrackData, SearchResultAdapter.SearchViewHolder>(SearchDiffCallback()) {


    class SearchViewHolder (val ui : SearchResultListItemBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind(item : SearchTrackData, clickListener : SearchClickListener) {
            ui.searchResult = item
            ui.searchResultTitle.text = item.track.title
            ui.searchResultDescription.text = item.track.subtitle
            Glide.with(itemView)
                    .load(item.track.images.coverart)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .into(ui.searchResultImg)

            ui.clickListener = clickListener
            ui.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup) : SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val ui = SearchResultListItemBinding.inflate(layoutInflater)

                return SearchViewHolder(ui)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = holder.bind(getItem(position), clickListener)



    class SearchDiffCallback : DiffUtil.ItemCallback<SearchTrackData>() {
        override fun areItemsTheSame(oldItem: SearchTrackData, newItem: SearchTrackData) = oldItem.track.key == newItem.track.key

        override fun areContentsTheSame(oldItem: SearchTrackData, newItem: SearchTrackData) = oldItem == newItem

    }


    class SearchClickListener(val clickListener : (term : SearchTrackData) -> Unit) {
        fun onClick (term : SearchTrackData) = clickListener(term)
    }



}