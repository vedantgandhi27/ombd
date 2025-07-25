package com.example.imbd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imbd.model.Movie
import com.example.imbd.model.MovieSearchResult
import com.example.imbd.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _fullMovieResults = MutableLiveData<List<Movie>>()
    val fullMovieResults: LiveData<List<Movie>> = _fullMovieResults

    // Below function searches for movies by title and fetches full movie details
    fun searchMovies(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val basicResults = MovieRepository.searchMoviesByTitle(title) ?: emptyList()
            val detailedResults = basicResults.mapNotNull { result: MovieSearchResult ->
                MovieRepository.getMovieById(result.imdbID)
            }
            _fullMovieResults.postValue(detailedResults)
        }
    }
}
