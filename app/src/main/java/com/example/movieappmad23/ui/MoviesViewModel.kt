package com.example.movieappmad23.ui

import androidx.lifecycle.ViewModel
import com.example.movieappmad23.data.FavoriteUiState
import com.example.movieappmad23.data.Movie
import com.example.movieappmad23.models.Genre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MoviesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState : StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun toggleFavorite(movie: Movie) {
        _uiState.update {curState ->
            movie.isFavorite = movie.isFavorite.not()
            val favorites = curState.favoriteMovies.toMutableList()
            if (movie.isFavorite) { favorites.add(movie)}
            else { favorites.remove(movie) }

            curState.copy(
                favoriteMovies = favorites.toList()
            )
        }
    }
    fun addMovie(
        title: String,
        year: String,
        genres: List<Genre>,
        director: String,
        actors: String,
        plot: String,
        rating: String
    ) {
        val newMovie = Movie(
            id = "tt00 + $title + $year",
            title = title,
            year = year,
            genre = genres,
            director = director,
            actors = actors,
            plot = plot,
            rating = rating.toFloat(),
            images = listOf("NoImages")
        )
        _uiState.update { curState ->
            val movies = curState.allMovies.toMutableList()
            movies.add(newMovie)
            curState.copy(
                allMovies = movies.toList()
            )
        }

    }
    fun getAllMovies(): List<Movie> {
        return _uiState.value.allMovies
    }

    fun getAllFavorites(): List<Movie> {
        return _uiState.value.favoriteMovies
    }
}