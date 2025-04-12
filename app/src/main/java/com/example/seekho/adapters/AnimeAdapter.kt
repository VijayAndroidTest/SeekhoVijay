package com.example.seekho.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seekho.R
import com.example.seekho.models.Anime
import com.squareup.picasso.Picasso

class AnimeAdapter(private val animeList: List<Anime>, private val onItemClick: (Anime) -> Unit) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {


    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.animeTitle)
        val episodesTextView: TextView = itemView.findViewById(R.id.animeEpisodes)
        val scoreTextView: TextView = itemView.findViewById(R.id.animeScore)
        val posterImageView: ImageView = itemView.findViewById(R.id.animePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.anim_listitem, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.titleTextView.text = anime.title
        holder.episodesTextView.text = "MAL ID: ${anime.mal_id}"
        holder.scoreTextView.text = "Score: ${anime.score ?: "N/A"}"


        val myPlaceholderImage = R.drawable.giconsign

        anime.images?.jpg?.image_url?.let { imageUrl ->
            Picasso.get()
                .load(imageUrl)
                .placeholder(myPlaceholderImage)
                .error(myPlaceholderImage)
                .into(holder.posterImageView)
        } ?: run {
            holder.posterImageView.setImageResource(myPlaceholderImage)
        }

        holder.itemView.setOnClickListener {
            onItemClick(anime)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

}