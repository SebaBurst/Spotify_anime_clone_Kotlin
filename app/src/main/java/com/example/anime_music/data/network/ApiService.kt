package com.example.anime_music.data.network

import com.example.anime_music.data.models.SongsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("songs.json")
    suspend fun getSongs(): SongsResult

    @GET("songs.json")
    suspend fun <SongsResultItem> getSongByQuery(@Query("orderBy") orderBy: String, @Query("equalTo") equalTo: String): SongsResultItem
}