package com.example.seekho.viemmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekho.apiurls.NetworkMonitor
import com.example.seekho.repository.AnimRepository
import com.example.seekho.models.Anime
import kotlinx.coroutines.launch

class AnimeViewModel:ViewModel() {
    private val repository = AnimRepository()
    val topAnime: LiveData<List<Anime>> = repository.getTopAnime()
    init {
        viewModelScope.launch {
            NetworkMonitor.isConnected.collect { connected ->
                if (connected) {
                    println("✅ Internet Available")
                } else {
                    println("❌ No Internet")
                }
            }
        }
    }
}