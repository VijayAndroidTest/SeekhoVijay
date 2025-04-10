package com.example.seekho.models

data class AnimeDetailsResponse (val data: AnimeDetailsModel)
data class Genre (val name: String)
data class Images (
    val jpg: Jpg?
)