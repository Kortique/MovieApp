package com.example.movieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.CategoryItemBinding
import com.example.movieapp.entities.CategoryWithMovies

class CategoryWithMoviesAdapter(
    private val categoriesWithMovies: List<CategoryWithMovies>
) : RecyclerView.Adapter<CategoryWithMoviesAdapter.CategoryWithMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryWithMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)

        return CategoryWithMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryWithMovieViewHolder, position: Int) {
        holder.bind(categoriesWithMovies[position])
    }

    override fun getItemCount(): Int = categoriesWithMovies.count()

    class CategoryWithMovieViewHolder(
        private val binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryWithMovies: CategoryWithMovies) {
            with(binding) {
                categoryTitle.text = categoryWithMovies.categoryTitle
                moviesRecyclerview.adapter = MoviesAdapter(categoryWithMovies.movies, categoryWithMovies.category)
            }
        }

    }


}