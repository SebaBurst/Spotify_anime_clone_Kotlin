package com.example.anime_music.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_music.data.models.SongsResult
import com.example.anime_music.data.models.SongsResultItem
import com.example.anime_music.domain.usecases.GetSongsUseCase
import kotlinx.coroutines.launch

// Implementar ViewModel de musica
class SongViewModel: ViewModel() {
    // Implementar ViewModel de musica
    val newSongs = MutableLiveData< List<SongsResultItem>>()
    //Llamamos a los casos de uso
    var getNewSongsUseCase = GetSongsUseCase()

    // Implementar ViewModel de musica
    fun onCreated(){
        // Implementar ViewModel de musica
        viewModelScope.launch {
            val result = getNewSongsUseCase()
            if(result.isNotEmpty()){
                newSongs.postValue(result)
            }
        }
    }






}