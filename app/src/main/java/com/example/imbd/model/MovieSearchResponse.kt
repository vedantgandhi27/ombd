package com.example.imbd.model

// Data class for storing data for API call
data class MovieSearchResponse(
    val Search: List<MovieSearchResult>?,
    val totalResults: String?,
    val Response: String
)
