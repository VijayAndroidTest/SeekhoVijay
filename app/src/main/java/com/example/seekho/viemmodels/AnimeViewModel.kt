package com.example.seekho.viemmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.seekho.repository.AnimRepository
import com.example.seekho.models.Anime

class AnimeViewModel:ViewModel() {
    private val repository = AnimRepository()
    val topAnime: LiveData<List<Anime>> = repository.getTopAnime()

}