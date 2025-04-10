package com.example.seekho.UIview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seekho.adapters.AnimeAdapter
import com.example.seekho.viemmodels.AnimeViewModel
import com.example.seekho.R
import com.example.seekho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var viewModel: AnimeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recycle.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[AnimeViewModel::class.java]

        viewModel.topAnime.observe(this, Observer { animList ->
            animeAdapter = AnimeAdapter(animList) { anime ->

                val intent = Intent(this@MainActivity, AnimDetailsActivity::class.java)
                intent.putExtra("animeId", anime.mal_id)
                startActivity(intent)
                Log.d("anime_Id", "Fetching details for animeId: $anime.mal_id")
            }
            binding.recycle.adapter = animeAdapter
        })

    }
}