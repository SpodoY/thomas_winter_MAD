package com.example.movieappmad23.ui

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.data.Movie
import com.example.movieappmad23.data.getMovies
import com.example.movieappmad23.models.Genre

class MoviesViewModel : ViewModel() {

    private val _allMovies = getMovies().toMutableStateList()
    val allMovies: MutableList<Movie>
        get() = _allMovies

    private val _favoriteMovies = mutableListOf<Movie>()
    val favoriteMovies: MutableList<Movie>
        get() = _favoriteMovies

    fun toggleFavorite(movie: Movie) {
        val index = _allMovies.indexOf(movie)
        _allMovies[index].isFavorite = _allMovies[index].isFavorite.not()

        if (_allMovies[index].isFavorite) {
            _favoriteMovies.add(movie)
        } else {
            _favoriteMovies.remove(movie)
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
        _allMovies.add(newMovie)

    }
    fun loadAllMovies(): List<Movie> {
        return _allMovies
    }

    fun getAllFavorites(): List<Movie> {
        return _favoriteMovies
    }

    fun stringNotEmpty(item: String): Boolean {
        Log.d("STRING_EMPTY", item.length.toString())
        return item.isNotEmpty()
    }

    fun floatNotEmpty(item: String): Boolean {
//        return item.isNaN().not()
        return true
    }

    fun <T> listNotEmpty(items: List<T>): Boolean {
        return items.isNotEmpty()
    }
}