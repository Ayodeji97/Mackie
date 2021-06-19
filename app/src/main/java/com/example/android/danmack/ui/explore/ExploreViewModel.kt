package com.example.android.danmack.ui.explore

import androidx.lifecycle.*
import com.example.android.danmack.model.SongData
import com.example.android.danmack.model.Track

import com.example.android.danmack.repository.SongRepository
import com.example.android.danmack.sample.Response
import com.example.android.danmack.sample.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor
    (private val songRepository: SongRepository) : ViewModel() {

    private val _tracksList = MutableLiveData<SongData>()
    val tracksList : LiveData<SongData>
        get() = _tracksList

    private val _recommendedSongList = MutableLiveData<SongData>()
    val recommendedSongList : LiveData<SongData>
        get() = _recommendedSongList


init {
    getAllSongs()
    getAllRecommendations()
}


    fun getAllSongs () {
        viewModelScope.launch {
            try {

           _tracksList.value = songRepository.getAllSongs()
                val see = songRepository.getAllSongs()
                Timber.i("SUCCESS")
               Timber.i("SEEE: $see")
            } catch (e : Exception) {

               Timber.e("FAILURE: ${e.localizedMessage}")
            }

        }
    }


    fun getAllRecommendations () {

        viewModelScope.launch {

            try {

                _recommendedSongList.value = songRepository.getAllRecommendations()

            }catch (e : Exception) {

                Timber.e("RECOMMENDATION_FAILURE: $e")
            }
        }
    }

}


@Suppress("UNCHECKED_CAST")
class SongViewModelFactory (
        private val songRepository: SongRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (ExploreViewModel(songRepository) as T)
}
