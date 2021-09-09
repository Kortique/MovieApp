package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.entities.AppState
import com.example.movieapp.entities.CategoryWithMovies
import com.example.movieapp.entities.MoviesCategory
import com.example.movieapp.ui.home.adapters.CategoryWithMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.appState.observe(viewLifecycleOwner) { appState ->
            appState?.let { processData(appState) }
        }

        homeViewModel.fetchData()
    }

    private fun processData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
                categoriesRecyclerview.visibility = View.GONE
                Snackbar.make(mainConstraint, "Loading", Snackbar.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                progressBar.visibility = View.GONE
                categoriesRecyclerview.visibility = View.VISIBLE

                val categoriesWithMovies = listOf(
                    CategoryWithMovies(
                        MoviesCategory.NOW_PLAYING,
                        getString(R.string.now_playing),
                        appState.nowPlaying
                    ),
                    CategoryWithMovies(
                        MoviesCategory.UPCOMING,
                        getString(R.string.upcoming),
                        appState.upcomingMovies
                    )
                )
                categoriesRecyclerview.adapter = CategoryWithMoviesAdapter(categoriesWithMovies)

                Snackbar.make(mainConstraint, "Success", Snackbar.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                categoriesRecyclerview.visibility = View.GONE
                Snackbar
                    .make(mainConstraint, "Error", Snackbar.LENGTH_SHORT)
                    .setAction("Reload") { homeViewModel.fetchData() }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}