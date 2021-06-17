package com.example.android.danmack.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.danmack.databinding.TrendingListItemBinding
import com.example.android.danmack.model.Song

class ExploreAdapter (val clickListener : SongClickListener) : ListAdapter<Song, ExploreAdapter.ExploreViewHolder> (SongDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder = ExploreViewHolder.from(parent)

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    /**
     * View Holder
     * */

    class ExploreViewHolder (val ui : TrendingListItemBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind (item : Song, clickListener : SongClickListener) {
            ui.song = item
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
    class SongDiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song) = oldItem.tracks == newItem.tracks
        override fun areContentsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem

    }


    /**
     * Asteroid click listener to handle click event
     * */
    class SongClickListener(val clickListener : (song : Song) -> Unit) {
        fun onClick (song: Song) = clickListener(song)
    }

}