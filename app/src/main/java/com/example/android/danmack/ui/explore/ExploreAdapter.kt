package com.example.android.danmack.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.danmack.databinding.TrendingListItemBinding
import com.example.android.danmack.model.SongData


class ExploreAdapter (val clickListener : SongClickListener) : ListAdapter<SongData, ExploreAdapter.ExploreViewHolder> (SongDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder = ExploreViewHolder.from(parent)

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    /**
     * View Holder
     * */

    class ExploreViewHolder (val ui : TrendingListItemBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind (item : SongData, clickListener : SongClickListener) {
           ui.songData = item
            ui.clickListener = clickListener
            ui.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup) : ExploreViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val ui = TrendingListItemBinding.inflate(layoutInflater, parent, false)
                return ExploreViewHolder(ui)
            }
        }
    }


    /**
     * Get the change in the recyclerview list items
     * */
    class SongDiffCallback : DiffUtil.ItemCallback<SongData>() {
        override fun areItemsTheSame(oldItem: SongData, newItem: SongData) = oldItem.tracks == newItem.tracks
        override fun areContentsTheSame(oldItem: SongData, newItem: SongData) = oldItem == newItem

    }


    /**
     * Asteroid click listener to handle click event
     * */
    class SongClickListener(val clickListener : (song : SongData) -> Unit) {
        fun onClick (song: SongData) = clickListener(song)
    }

}