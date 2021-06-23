package com.example.android.danmack.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.danmack.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val viewModel : ExploreViewModel by viewModels()
    private lateinit var exploreVerticalAdapter: ExploreAdapter
    private lateinit var exploreHorizontalAdapter: ExploreAdapter
    private lateinit var ui : FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ui = FragmentExploreBinding.inflate(inflater)

        ui.lifecycleOwner = this
        ui.exploreViewModel = viewModel

        val verticalManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val horizontalmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        exploreVerticalAdapter = ExploreAdapter(ExploreAdapter.SongClickListener {track ->
            viewModel.onTopTrendSongClicked(track)
        })

        exploreHorizontalAdapter = ExploreAdapter(ExploreAdapter.SongClickListener {track->
            viewModel.onRecommendationSongClicked(track)
        })



        // vertical orientation
        ui.topTrendingRecyclerView.layoutManager = verticalManager

        // horinzontal orientation
       ui.recommendationRecyclerView.layoutManager = horizontalmanager

        ui.recommendationRecyclerView.adapter = exploreHorizontalAdapter
        ui.topTrendingRecyclerView.adapter = exploreVerticalAdapter


        viewModel.allTrackList.observe(viewLifecycleOwner, Observer {
            exploreVerticalAdapter.submitList(it)
        })


        viewModel.allRecommendationsList.observe(viewLifecycleOwner, Observer {
            exploreHorizontalAdapter.submitList(it)
        })


        viewModel.navigateToTopTrendDataDetail.observe(viewLifecycleOwner, Observer {track->
            track?.let {
                this.findNavController().navigate(ExploreFragmentDirections.actionExploreFragmentToExploreDetailFragment(track))
                viewModel.onNavigatedFinish()
            }
        })



        return ui.root
    }

}