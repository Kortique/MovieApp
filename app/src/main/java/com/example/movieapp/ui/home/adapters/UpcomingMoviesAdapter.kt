package com.example.movieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.UpcomingMovieItemBinding
import com.example.movieapp.entities.Movie
import com.example.movieapp.utils.toString

class UpcomingMoviesAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<UpcomingMoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UpcomingMovieItemBinding.inflate(layoutInflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.count()

    class MovieViewHolder(
        private val binding: UpcomingMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate.toString("yyyy-MM-dd")
            }
        }

    }


}