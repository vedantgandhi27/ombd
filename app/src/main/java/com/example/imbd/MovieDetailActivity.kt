package com.example.imbd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.imbd.databinding.ActivityMovieDetailBinding
import com.example.imbd.model.Movie
import com.example.imbd.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

//    Fetches movie by title
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieTitle = intent.getStringExtra("movieTitle")

        CoroutineScope(Dispatchers.IO).launch {
            val movie: Movie? = MovieRepository.getMovieByTitle(movieTitle ?: "")
            runOnUiThread {
                if (movie != null) {
                    bindMovieDetails(movie)
                } else {
                    binding.titleTextView.text = "Movie not found."
                }
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

//    Displays following informatio
    private fun bindMovieDetails(movie: Movie) {
        binding.titleTextView.text = "Title: ${movie.Title}"
        binding.yearTextView.text = "Year: ${movie.Year}"
        binding.ratedTextView.text = "Rated: ${movie.Rated}"
        binding.directorTextView.text = "Director: ${movie.Director}"
        binding.plotTextView.text = "Plot: ${movie.Plot}"
        binding.moviePoster.load(movie.Poster)
    }
}
