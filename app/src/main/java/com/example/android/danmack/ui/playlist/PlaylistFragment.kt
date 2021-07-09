package com.example.android.danmack.ui.playlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentPlaylistBinding
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.ui.explore.ExploreAdapter
import com.example.android.danmack.ui.explore.ExploreFragmentDirections
import com.example.android.danmack.ui.exploredetail.ExploreDetailViewModel
import com.example.android.danmack.utils.setDisplayHomeAsUpEnabled
import com.example.android.danmack.utils.setTitle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Platlist fragment
 * */
@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private lateinit var ui : FragmentPlaylistBinding

    private lateinit var exploreAdapter: ExploreAdapter

    private val viewModel : ExploreDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ui = FragmentPlaylistBinding.inflate(inflater)

        ui.lifecycleOwner = this
        ui.exploreDetailViewModel = viewModel

        val horizontalManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        exploreAdapter = ExploreAdapter(ExploreAdapter.SongClickListener {

           viewModel.onPlayListSongClicked(it)

        })

        ui.playlistRv.layoutManager = horizontalManager

        ui.playlistRv.adapter = exploreAdapter

        viewModel.playLists.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()) {
                ui.noDataTextView.visibility = View.VISIBLE
            } else {
                ui.noDataTextView.visibility = View.GONE
                exploreAdapter.submitList(it)
            }

        })

        viewModel.navigateToTopTrendDataDetail.observe(viewLifecycleOwner, Observer {track->
            track?.let {
                this.findNavController().navigate(PlaylistFragmentDirections.actionPlaylistFragmentToExploreDetailFragment(track))
                viewModel.onNavigatedFinish()
            }
        })


       return ui.root
    }


}