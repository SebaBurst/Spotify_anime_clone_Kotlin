package com.example.anime_music.data.network.repositories

import com.example.anime_music.data.network.MusicService

class MusicRepository {

    // Implementar repositorio de musica
    private val api = MusicService()
    // Implementar repositorio de musica
    suspend fun getSongs() = api.getSongs()

    //obtener cancion por id
    suspend fun getSongById(id: String) = api.getSongById(id)
}