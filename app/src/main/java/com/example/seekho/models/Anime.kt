package com.example.seekho.models

data class Anime (
    val mal_id:Int,
    val title:String,
    val episode_count:Int?,
    val score : Double?,
    val images : Images?
)


data class Jpg (
    val image_url:String?
)
data class TopAnimResponse (val data : List<Anime>)
data class Trailer(val youtube_id: String?  )

