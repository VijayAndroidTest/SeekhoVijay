package com.example.seekho.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekho.repository.AnimDetailsRepository
import com.example.seekho.viemmodels.AnimDetailsViewModel

class AnimDetailsViewModelFactory(private val repository: AnimDetailsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}