package com.example.android.danmack.ui.search

import android.opengl.Visibility
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
import com.example.android.danmack.databinding.FragmentSearchBinding
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.ui.explore.ExploreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var ui : FragmentSearchBinding

    private val viewModel : SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchResultAdapter: SearchResultAdapter
//    private lateinit var exploreAdapter: ExploreAdapter


    private lateinit var userSearchInput : String



    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ui = FragmentSearchBinding.inflate(inflater)


        autoSearchSongs()
        searchSongResult()

        ui.lifecycleOwner = this
        ui.searchViewModel = viewModel

        searchAdapter = SearchAdapter(SearchAdapter.SearchClickListener {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            ui.searchView.setQuery(it.term, true)

            userSearchInput = ui.searchView.query.toString()


        })

        searchResultAdapter = SearchResultAdapter(SearchResultAdapter.SearchClickListener {

        })

        val verticalManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val anotherVerticalManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        ui.searchRecyclerView.adapter = searchAdapter
        ui.searchResultRecyclerView.adapter = searchResultAdapter

        ui.searchRecyclerView.layoutManager = verticalManager
        ui.searchResultRecyclerView.layoutManager = anotherVerticalManager




        viewModel.autoSearchResultList.observe(viewLifecycleOwner, Observer {
            ui.searchRecyclerView.visibility = View.VISIBLE
            ui.searchResultRecyclerView.visibility = View.GONE
            searchAdapter.submitList(it?.hints)
        })

        viewModel.searchResultList.observe(viewLifecycleOwner, Observer {
            ui.searchRecyclerView.visibility = View.GONE
            ui.searchResultRecyclerView.visibility = View.VISIBLE
            searchResultAdapter.submitList(it?.hits)

        })

        return ui.root

    }



    private fun autoSearchSongs (){
        ui.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    if (it.isEmpty()) {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.autoCompleteSearchResult(it)
//                        userSearchInput = it
                    }
                }
            }
        )
    }

    private fun searchSongResult () {
        ui.searchButton.setOnClickListener {
            Toast.makeText(requireContext(), "$userSearchInput", Toast.LENGTH_LONG).show()
            Timber.i("UserInput: $userSearchInput")
            viewModel.searchResult(userSearchInput)
        }
    }



}