package com.example.imbd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imbd.adapter.MovieAdapter
import com.example.imbd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Added ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        // Added RecyclerView Adapter with click handler
        movieAdapter = MovieAdapter { selectedMovie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movieTitle", selectedMovie.Title)
            startActivity(intent)
        }

        // Set up RecyclerView
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        // Search Button
        binding.searchButton.setOnClickListener {
            val searchTitle = binding.searchEditText.text.toString().trim()
            if (searchTitle.isNotEmpty()) {
                movieViewModel.searchMovies(searchTitle)
            }
        }

        //  LiveData and adapter
        movieViewModel.fullMovieResults.observe(this, Observer { movies ->
            movieAdapter.submitList(movies)
        })
    }
}
