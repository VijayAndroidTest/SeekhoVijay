package com.example.seekho.apiurls

import com.example.seekho.models.AnimeDetailsResponse
import com.example.seekho.models.TopAnimResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMethod {
    @GET("top/anime")
    fun getTopAnime(): Call<TopAnimResponse>

    @GET("anime/{anime_id}")
    fun getAnimeDetails(@Path("anime_id") animeId: Int): Call<AnimeDetailsResponse>
}