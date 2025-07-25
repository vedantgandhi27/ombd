package com.example.imbd.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imbd.databinding.ItemMovieBinding
import com.example.imbd.model.Movie

class MovieAdapter(private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = listOf<Movie>()

    fun submitList(movies: List<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }
    //    Class to hold reference to each item view and add binding to them
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
    //         Sets title,Year,Studio and rating
        fun bind(movie: Movie) {
            binding.titleTextView.text = "Title: ${movie.Title}"
            binding.titleTextView.setTextColor(Color.BLACK)
            binding.titleTextView.textSize = 20f
            binding.titleTextView.setTypeface(binding.titleTextView.typeface, android.graphics.Typeface.BOLD)

            binding.yearTextView.text = "Year: ${movie.Year}"
            binding.yearTextView.setTextColor(Color.DKGRAY)

            binding.productionTextView.text = "Studio: ${movie.Production}"
            binding.productionTextView.setTextColor(Color.DKGRAY)

            binding.ratedTextView.text = "Rating: ${movie.Rated}"
            binding.ratedTextView.setTextColor(Color.DKGRAY)


            binding.root.setPadding(12, 12, 12, 24)

            binding.root.setOnClickListener {
                onClick(movie)
            }
        }
    }
    // Adds item layout  and get view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    // Binds the data to the view
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
    // Gets total number of movies
    override fun getItemCount(): Int = movieList.size
}
