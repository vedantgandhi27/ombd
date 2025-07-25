package com.example.imbd.repository

import com.example.imbd.model.Movie
import com.example.imbd.model.MovieSearchResponse
import com.example.imbd.model.MovieSearchResult
import com.example.imbd.network.ApiClient
import com.google.gson.Gson

object MovieRepository {

    // Fetches a full Movie by its title
    fun getMovieByTitle(title: String): Movie? {
        val json = ApiClient.fetchMovieByTitle(title)
        return if (json != null) {
            try {
                Gson().fromJson(json, Movie::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    // Fetches a list of search results using search query
    fun searchMoviesByTitle(title: String): List<MovieSearchResult>? {
        val urlString = "https://www.omdbapi.com/?apikey=2b3bcad6&s=${title.replace(" ", "+")}"
        val json = ApiClient.fetchJsonFromUrl(urlString)
        return if (json != null) {
            try {
                val response = Gson().fromJson(json, MovieSearchResponse::class.java)
                if (response.Response == "True") response.Search else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else null
    }

    // Fetches movie details by IMDb ID for activity movie details
    fun getMovieById(imdbID: String): Movie? {
        val urlString = "https://www.omdbapi.com/?apikey=2b3bcad6&i=$imdbID"
        val json = ApiClient.fetchJsonFromUrl(urlString)
        return if (json != null) {
            try {
                Gson().fromJson(json, Movie::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else null
    }



}