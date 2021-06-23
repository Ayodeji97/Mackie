package com.example.android.danmack.ui.explore

import androidx.lifecycle.*
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.network.networkmodel.NetworkTrackEntity

import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor
    (private val songRepository: SongRepository) : ViewModel() {

    private val _tracksList = MutableLiveData<List<NetworkTrackEntity>>()
    val tracksList : LiveData<List<NetworkTrackEntity>>
        get() = _tracksList

    private val _recommendedSongList = MutableLiveData<List<NetworkTrackEntity>>()
    val recommendedSongList : LiveData<List<NetworkTrackEntity>>
        get() = _recommendedSongList


    private val _navigateToTopTrendDataDetail = MutableLiveData<Track?>()
    val navigateToTopTrendDataDetail : LiveData<Track?>
        get() = _navigateToTopTrendDataDetail




    val allTrackList = songRepository.allTopTrends
    val allRecommendationsList = songRepository.allRecommendations


init {
    getRefreshTracksAndRecommendations()
}


    private fun getRefreshTracksAndRecommendations () {
        viewModelScope.launch {
//            songRepository.refreshTracks()
//            songRepository.refreshRecommendations()
           // songRepository.autoCompleteSearchSongs()
            songRepository.searchSongs()

        }
    }

    fun onNavigatedFinish() {
        _navigateToTopTrendDataDetail.value = null
    }

    fun onTopTrendSongClicked (track: Track) {
        _navigateToTopTrendDataDetail.value = track

    }

    fun onRecommendationSongClicked (track: Track) {
        _navigateToTopTrendDataDetail.value = track
    }



    suspend fun searchResult () {

    }


}


@Suppress("UNCHECKED_CAST")
class SongViewModelFactory (
        private val songRepository: SongRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (ExploreViewModel(songRepository) as T)
}
