package com.example.android.danmack.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android.danmack.MyBottomSheetDialogFragment
import com.example.android.danmack.databinding.TrendingListItemBinding
import com.example.android.danmack.model.SongData
import com.example.android.danmack.model.Track
import com.google.android.material.bottomsheet.BottomSheetDialog


class ExploreAdapter (val clickListener : SongClickListener) : ListAdapter<Track, ExploreAdapter.ExploreViewHolder> (SongDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder = ExploreViewHolder.from(parent)

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    /**
     * View Holder
     * */

    class ExploreViewHolder (val ui : TrendingListItemBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind (item : Track, clickListener : SongClickListener) {
            ui.songData = item
            ui.trendingListItemTitleTv.text = item.title
            ui.trendingListItemSubtitleTv.text = item.subtitle
            Glide.with(itemView)
                    .load(item.images.coverart)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ui.trendingRecyclerViewImg)


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
    class SongDiffCallback : DiffUtil.ItemCallback<Track>() {

        override fun areItemsTheSame(oldItem: Track, newItem: Track) = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Track, newItem: Track) = oldItem == newItem

    }


    /**
     * Asteroid click listener to handle click event
     * */
    class SongClickListener(val clickListener : (song : Track) -> Unit) {
        fun onClick (song: Track) = clickListener(song)
    }

}