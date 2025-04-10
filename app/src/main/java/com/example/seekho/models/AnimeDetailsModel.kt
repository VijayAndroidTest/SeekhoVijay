package com.example.seekho.models

 data class AnimeDetailsModel (
    val title: String,
    val synopsis: String,
    val genres: List<Genre>,
    val episodes: Int?,
    val score: Double?,
    val trailer: Trailer?,
    val images: Images,  val rating: String?,
)