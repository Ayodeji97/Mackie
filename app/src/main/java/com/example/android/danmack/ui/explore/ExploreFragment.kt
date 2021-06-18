package com.example.android.danmack.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentExploreBinding
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val viewModel : ExploreViewModel by viewModels()
    private lateinit var exploreAdapter: ExploreAdapter
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

        exploreAdapter = ExploreAdapter(ExploreAdapter.SongClickListener {
            Timber.i("Click")
        })

        // vertical orientation
        ui.topTrendingRecyclerView.layoutManager = verticalManager

        // horinzontal orientation
        ui.recommendationRecyclerView.layoutManager = horizontalmanager


        ui.topTrendingRecyclerView.adapter = exploreAdapter

        viewModel.tracksList.observe(viewLifecycleOwner, Observer {

           Timber.i("{TRACKLIST : ${it}}")
          //  exploreAdapter.submitList(it)
           // exploreAdapter.submitList(it)
        })

       viewModel.getAllSongs()



        return ui.root
    }




}