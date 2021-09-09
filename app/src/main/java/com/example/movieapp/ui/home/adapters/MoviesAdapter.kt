package com.example.movieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.NowPlayingMovieItemBinding
import com.example.movieapp.databinding.UpcomingMovieItemBinding
import com.example.movieapp.entities.Movie
import com.example.movieapp.entities.MoviesCategory
import com.example.movieapp.utils.toString

class MoviesAdapter(
    private val movies: List<Movie>,
    private val moviesCategory: MoviesCategory
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        when (moviesCategory) {
            MoviesCategory.NOW_PLAYING -> {
                val binding = NowPlayingMovieItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )

                NowPlayingMovieViewHolder(binding)
            }
            MoviesCategory.UPCOMING -> {
                val binding = UpcomingMovieItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )

                UpcomingMovieViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holderNowPlaying: MovieViewHolder, position: Int) {
        holderNowPlaying.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.count()

    abstract class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(movie: Movie)
    }

    class NowPlayingMovieViewHolder(
        private val binding: NowPlayingMovieItemBinding
    ) : MovieViewHolder(binding.root) {

        override fun bind(movie: Movie) {
            with(binding) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate.toString("yyyy-MM-dd")
                moviePopularity.text = movie.popularity.toString()
            }
        }

    }

    class UpcomingMovieViewHolder(
        private val binding: UpcomingMovieItemBinding
    ) : MovieViewHolder(binding.root) {

        override fun bind(movie: Movie) {
            with(binding) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate.toString("yyyy-MM-dd")
            }
        }

    }
}