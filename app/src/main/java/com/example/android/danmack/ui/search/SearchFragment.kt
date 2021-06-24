package com.example.android.danmack.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.danmack.DebouncingQueryTextListener
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentSearchBinding
import com.example.android.danmack.ui.explore.ExploreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var ui : FragmentSearchBinding

    private val viewModel : SearchViewModel by viewModels()

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_search, container, false)
        ui = FragmentSearchBinding.inflate(inflater)


        searchSongs()
        ui.lifecycleOwner = this
        ui.searchViewModel = viewModel


      searchAdapter = SearchAdapter(SearchAdapter.SearchClickListener {

      })

        val verticalManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        ui.searchRecyclerView.adapter = searchAdapter

        ui.searchRecyclerView.layoutManager = verticalManager
        return ui.root


    }

    fun searchSongs (){
        ui.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    if (it.isEmpty()) {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.searchResult(it)

                       // Toast.makeText(requireContext(), "$result", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

}