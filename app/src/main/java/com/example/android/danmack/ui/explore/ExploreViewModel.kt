package com.example.android.danmack.ui.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor
    (private val songRepository: SongRepository) : ViewModel() {


//init {
//   getAllSongs()
//}


    private fun getAllSongs () {
        viewModelScope.launch {
            try {
                val see = songRepository.getAllSongs()
                print(see)
            } catch (e : Exception) {
               Timber.e("$e")
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
