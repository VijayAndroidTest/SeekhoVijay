package com.example.seekho.viemmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekho.models.AnimeDetailsModel
import com.example.seekho.repository.AnimDetailsRepository
import kotlinx.coroutines.launch

class AnimDetailsViewModel(private val repository: AnimDetailsRepository) : ViewModel() {

    private val _animeDetails = MutableLiveData<AnimeDetailsModel?>()
    val animeDetails: LiveData<AnimeDetailsModel?> = _animeDetails

    var isLoading = false

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            isLoading = true
            _animeDetails.value = null
            try {
                val response = repository.getAnimeDetails(animeId)
                _animeDetails.value = response.data
            } catch (e: Exception) {
                _animeDetails.value = null
            } finally {
                isLoading = false
            }
        }
    }
}