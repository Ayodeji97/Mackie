package com.example.android.danmack.ui.search

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.TermData
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor
    (private val songRepository: SongRepository) : ViewModel() {


    private val _searchResultList = MutableLiveData<AutoComplete>()

    private val query : String = ""

    val searchResultList : LiveData<AutoComplete>
    get() = _searchResultList

    init {
        searchResult(query)
    }


    fun searchResult (query : String) {
        viewModelScope.launch {
           songRepository.autoCompleteSearchSongs(query)
        }
    }
}