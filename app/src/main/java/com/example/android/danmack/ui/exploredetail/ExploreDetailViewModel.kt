package com.example.android.danmack.ui.exploredetail

import androidx.lifecycle.ViewModel
import com.example.android.danmack.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreDetailViewModel @Inject constructor
(private val songRepository: SongRepository) : ViewModel() {

    // TODO : play song should direct to web view



    // TODO : when playlist is clicked, song should get saved to playlist fragment
}