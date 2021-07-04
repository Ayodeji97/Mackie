package com.example.android.danmack.ui.exploredetail

import android.util.Log
import androidx.lifecycle.*
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.model.searchmodel.HitsData
import com.example.android.danmack.repository.SongRepository
import com.example.android.danmack.ui.explore.ExploreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ExploreDetailViewModel @Inject constructor
(private val songRepository: SongRepository) : ViewModel() {

    // TODO : play song should direct to web view


    // TODO : when playlist is clicked, song should get saved to playlist fragment

    val playlists = songRepository.getAllPlayLists()

    private val _idValue = MutableLiveData<LocalTrackEntity>()

    val idValue : LiveData<LocalTrackEntity>
        get() = _idValue







    fun onPlayListClicked (localTrackEntity: LocalTrackEntity) {
        val currentPlayList = localTrackEntity.isPlayListSelected
        Log.d("UPDATE", currentPlayList.toString())
        println("UPDATE, $currentPlayList")
        val updatedPlayList = localTrackEntity.copy(isPlayListSelected = !currentPlayList)
        viewModelScope.launch {
            songRepository.updatePlayList(updatedPlayList)
        }
    }

    fun getTrackSelected (id : String)   {
        viewModelScope.launch {
            _idValue.value = songRepository.getTrackSelectedById(id)!!
        }

    }

}



@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory (
        private val songRepository: SongRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (ExploreViewModel(songRepository) as T)
}