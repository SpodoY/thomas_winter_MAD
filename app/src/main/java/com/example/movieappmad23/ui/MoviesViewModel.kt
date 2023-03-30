package com.example.movieappmad23.ui

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.R
import com.example.movieappmad23.data.Movie
import com.example.movieappmad23.data.getMovies
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable

class MoviesViewModel : ViewModel() {

    private val _allMovies = getMovies().toMutableStateList()
    val allMovies: List<Movie>
        get() = _allMovies

    private val _favoriteMovies = mutableListOf<Movie>().toMutableStateList()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies

    var title = mutableStateOf("")
    val year =  mutableStateOf("")
    var director = mutableStateOf("")
    var actors = mutableStateOf("")
    var plot = mutableStateOf("")
    var rating = mutableStateOf("")
    var isEnabledSaveButton = mutableStateOf(true)

    fun toggleFavorite(movie: Movie) {
        _allMovies.find { it.id == movie.id }?.let { task ->
            task.isFavorite = !task.isFavorite
            if (task.isFavorite) {
                _favoriteMovies.add(movie)
            } else {
                _favoriteMovies.remove(movie)
            }
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
            rating = rating.toFloat(), images = listOf(
                "https://kinderzeitung.kleinezeitung.at/wp-content/uploads/2022/06/adobestock_173371622min-1140x662.jpg",
            )
        )
        _allMovies.add(newMovie)

    }

    fun stringNotEmpty(item: String): Boolean {
        return item.isEmpty()
    }

    fun floatNotEmpty(item: String): Boolean {
        item.toFloatOrNull() ?: return item.isEmpty()
        return item.isEmpty()
    }

    fun <T> listNotEmpty(items: List<T>): Boolean {
        return items.isEmpty()
    }
}