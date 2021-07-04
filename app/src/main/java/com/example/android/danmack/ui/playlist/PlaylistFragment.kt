package com.example.android.danmack.ui.playlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentPlaylistBinding
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.ui.explore.ExploreAdapter
import com.example.android.danmack.ui.exploredetail.ExploreDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private lateinit var ui : FragmentPlaylistBinding

    private lateinit var exploreAdapter: ExploreAdapter

   // private val viewModel : PlayListViewModel by viewModels()

    private val viewModel : ExploreDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_playlist, container, false)

        ui = FragmentPlaylistBinding.inflate(inflater)

      //  ui.playListViewModel = viewModel
        ui.lifecycleOwner = this

        exploreAdapter = ExploreAdapter(ExploreAdapter.SongClickListener {

        })



        viewModel.playlists.observe(viewLifecycleOwner, Observer {

            //exploreAdapter.submitList(it as List<Track>)

        })

        viewModel.idValue.observe(viewLifecycleOwner, Observer {
            Log.i("INPLAYLIST", "$it")
        })


       return ui.root
    }


}