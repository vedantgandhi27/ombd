package com.example.imbd.model

// Data class for movie with details fetched from API
data class Movie(
    val Title: String,
    val Year: String,
    val Rated: String,
    val Production: String,
    val imdbID: String,
    val Director: String,
    val Plot: String,
    val Poster: String
)
