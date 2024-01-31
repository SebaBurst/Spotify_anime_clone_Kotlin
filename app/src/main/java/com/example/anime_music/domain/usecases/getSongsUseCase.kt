package com.example.anime_music.domain.usecases

import com.example.anime_music.data.models.SongsResult
import com.example.anime_music.data.models.SongsResultItem
import com.example.anime_music.data.network.repositories.MusicRepository

class GetSongsUseCase {

 // Implementar caso de uso de musica
    private val repository = MusicRepository()

    // Implementar caso de uso de musica
    suspend operator fun invoke() = repository.getSongs()


}