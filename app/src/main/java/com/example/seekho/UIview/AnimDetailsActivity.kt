package com.example.seekho.UIview

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.seekho.databinding.ActivityAnimDetailsBinding
import com.example.seekho.models.AnimeDetailsModel
import com.example.seekho.repository.AnimDetailsRepository
import com.example.seekho.repository.AnimDetailsViewModelFactory
import com.example.seekho.viemmodels.AnimDetailsViewModel

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.squareup.picasso.Picasso

class AnimDetailsActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private lateinit var binding: ActivityAnimDetailsBinding
    private lateinit var viewModel: AnimDetailsViewModel

    companion object {
        fun start(context: Context, animeId: Int) {
            val intent = Intent(context, AnimDetailsActivity::class.java)
            intent.putExtra("animeId", animeId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeId = intent.getIntExtra("animeId", -1)

        val repository = AnimDetailsRepository()
        val factory = AnimDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AnimDetailsViewModel::class.java]

        viewModel.fetchAnimeDetails(animeId)

        val dummyAnimeDetails: AnimeDetailsModel? = null
        viewModel.animeDetails.observe(this, Observer { animeDetails ->
        if (animeDetails == null) {
                if (viewModel.isLoading) {
                    Log.d("API_RESULT", "Loading: Fetching anime details...")
                } else {
                    Log.e("API_RESULT", "Failure: Error fetching details or null response")
                }
            } else {
                Log.d("API_RESULT", "Success: Anime details fetched successfully")
                bindData(animeDetails)
            }
        })
    }

    private fun bindData(animeDetails: AnimeDetailsModel) {
        binding.detailTitle.text = animeDetails.title
        binding.detailSynopsis.text = animeDetails.synopsis
        binding.detailGenres.text = "Genres: ${animeDetails.genres.joinToString(", ") { it.name }}"
        binding.detailEpisodes.text = "Episodes: ${animeDetails.episodes ?: "Unknown"}"
        binding.detailScore.text = "Rating: ${animeDetails.rating ?: "N/A"}"

        if (animeDetails.trailer?.youtube_id != null) {
            val youtubeId = animeDetails.trailer.youtube_id
            val youtubeUrl = "https://www.youtube.com/watch?v=${youtubeId}"

            try {
                if(player == null) {
                    player = ExoPlayer.Builder(this).build()
                    binding.detailPlayerView.player = player
                    val mediaItem = MediaItem.fromUri(Uri.parse(youtubeUrl))
                    player?.setMediaItem(mediaItem)
                    player?.prepare()
                }
                binding.detailPlayerView.visibility = View.VISIBLE
                binding.detailImageView.visibility = View.GONE
                binding.detailWebView.visibility = View.GONE;
                binding.playButton.visibility = View.GONE;

                player?.addListener(object : com.google.android.exoplayer2.Player.Listener {
                    override fun onPlayerError(error: com.google.android.exoplayer2.PlaybackException) {
                        Log.e("ExoPlayer", "Playback error: ${error.message}", error)
                        binding.detailPlayerView.visibility = View.GONE;
                        binding.playButton.visibility = View.VISIBLE;
                    }
                })

            } catch (e: Exception) {
                Log.e("ExoPlayer", "ExoPlayer setup error: ${e.message}", e)
                binding.detailPlayerView.visibility = View.GONE;
                binding.playButton.visibility = View.VISIBLE;
            }

            binding.playButton.setOnClickListener {
                binding.detailWebView.loadUrl(youtubeUrl);
                binding.detailWebView.webViewClient = WebViewClient()
                binding.detailWebView.settings.javaScriptEnabled = true
                binding.detailWebView.visibility = View.VISIBLE;
                binding.playButton.visibility = View.GONE;
            }

        } else {
            Picasso.get().load(animeDetails.images.jpg?.image_url).into(binding.detailImageView)
            binding.detailImageView.visibility = View.VISIBLE
            binding.detailPlayerView.visibility = View.GONE
            binding.detailWebView.visibility = View.GONE;
            binding.playButton.visibility = View.GONE;
        }
    }


    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}