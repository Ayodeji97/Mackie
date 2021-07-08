package com.example.android.danmack.ui.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor
(private val songRepository: SongRepository) : ViewModel() {

  //  val playlists = songRepository.getAllPlayLists()




    fun onPlayListClicked (localTrackEntity: LocalTrackEntity) {
        val currentPlayList = localTrackEntity.isPlayListSelected
        val updatedPlayList = localTrackEntity.copy(isPlayListSelected =! currentPlayList)
        viewModelScope.launch {
            songRepository.updatePlayList(updatedPlayList)
        }
    }
}