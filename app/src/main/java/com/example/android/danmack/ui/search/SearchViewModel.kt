package com.example.android.danmack.ui.search

import androidx.lifecycle.*
import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.HitsData
import com.example.android.danmack.model.searchmodel.SearchData
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor
    (private val songRepository: SongRepository) : ViewModel() {


    private val _autoSearchResultList = MutableLiveData<AutoComplete>()
    private val _searchResultList = MutableLiveData<HitsData>()


    private val query : String = "kiss"

    val autoSearchResultList : LiveData<AutoComplete>
    get() = _autoSearchResultList

    val searchResultList : LiveData<HitsData>
        get() = _searchResultList

    init {
        searchResult(query)
       autoCompleteSearchResult(query)
    }


    fun autoCompleteSearchResult (query : String) {
        viewModelScope.launch {
          _autoSearchResultList.value =  songRepository.autoCompleteSearchSongs(query)
        }
    }


    fun searchResult (query : String) {
        viewModelScope.launch {
          _searchResultList.value =  songRepository.searchSongs(query).tracks
        }
    }


}