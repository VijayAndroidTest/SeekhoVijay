package com.example.seekho.repository

import android.util.Log
import com.example.seekho.apiurls.ApiService
import com.example.seekho.models.AnimeDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class AnimDetailsRepository {

    suspend fun getAnimeDetails(animeId: Int): AnimeDetailsResponse {
        return withContext(Dispatchers.IO) {
            val response = ApiService.api.getAnimeDetails(animeId).await()
            Log.d("AnimDetailsRepository", "API Response: $response")
            response
        }
    }
}