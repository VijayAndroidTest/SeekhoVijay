package com.example.seekho.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seekho.apiurls.ApiService
import com.example.seekho.models.Anime
import com.example.seekho.models.TopAnimResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class AnimRepository {

        fun getTopAnime(): LiveData<List<Anime>> {
            val data = MutableLiveData<List<Anime>>()
            ApiService.api.getTopAnime().enqueue(object : Callback<TopAnimResponse> {
                override fun onResponse(call: Call<TopAnimResponse>, response: Response<TopAnimResponse>) {
                    if (response.isSuccessful) {
                        Log.d("API_RESPONSE", response.body().toString())
                        data.value = response.body()?.data ?: emptyList()
                    } else {
                        data.value = emptyList()
                    }
                }

                override fun onFailure(call: Call<TopAnimResponse>, t: Throwable) {
                    data.value = emptyList()             }
            })
            return data
        }
    }