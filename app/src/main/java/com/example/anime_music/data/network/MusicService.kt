package com.example.anime_music.data.network

import com.example.anime_music.data.models.SongsResult
import com.example.anime_music.data.models.SongsResultItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MusicService {
    // Implementar servicio de musica
    // Llamar a la funcion getSongs() del servicio
    private val retrofit = RetrofitClient().getRetrofitClient()

    // Implementar servicio de musica
    suspend fun getSongs(): SongsResult {
        return withContext(Dispatchers.IO){
            val responseResult: SongsResult = retrofit.create(ApiService::class.java).getSongs()
            responseResult
        }
    }

    //obtener cancion por id
    suspend fun getSongById(id: String): SongsResultItem {
        return withContext(Dispatchers.IO){
            val responseResult: SongsResultItem = retrofit.create(ApiService::class.java).getSongByQuery("id", id)
            responseResult
        }
    }






}