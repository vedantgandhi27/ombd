package com.example.imbd.network

import java.net.HttpURLConnection
import java.net.URL

object ApiClient {
    //    API url and key
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val API_KEY = "2b3bcad6"

    // Below function fetches movie details by title from the OMDB API
    fun fetchMovieByTitle(title: String): String? {
        val urlString = "$BASE_URL?apikey=$API_KEY&t=${title.replace(" ", "+")}"
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            // Fetches the response as the string
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // This function fetches raw JSON from any given URL
    fun fetchJsonFromUrl(urlString: String): String? {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
