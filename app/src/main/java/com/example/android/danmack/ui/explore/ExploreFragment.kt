package com.example.android.danmack.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentExploreBinding
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val viewModel : ExploreViewModel by viewModels()
    private lateinit var ui : FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_explore, container, false)
        ui = FragmentExploreBinding.inflate(inflater)

        ui.lifecycleOwner = this

        ui.exploreViewModel = viewModel

       // viewModel.getAllSongs()

        return ui.root
    }




}