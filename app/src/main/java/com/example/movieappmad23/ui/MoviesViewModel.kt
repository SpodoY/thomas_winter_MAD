package com.example.movieappmad23.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
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

    private var addMovie: Movie = Movie("", "", "", listOf(), "", "", "", listOf(), 0.0f)

    var title = mutableStateOf(addMovie.title)
    var titleError: MutableState<Boolean> = mutableStateOf(false)

    val year = mutableStateOf(addMovie.year)
    var yearError: MutableState<Boolean> = mutableStateOf(false)

    var director = mutableStateOf(addMovie.director)
    var directorError: MutableState<Boolean> = mutableStateOf(false)

    var actors = mutableStateOf(addMovie.actors)
    var actorsError: MutableState<Boolean> = mutableStateOf(false)

    var plot = mutableStateOf(addMovie.plot)
    var plotError: MutableState<Boolean> = mutableStateOf(false)

    var rating = mutableStateOf(addMovie.rating.toString().replace("0.0", ""))
    var ratingError: MutableState<Boolean> = mutableStateOf(false)

    var isEnabledAddButton: MutableState<Boolean> = mutableStateOf(false)

    var genreItems = mutableStateOf(
        Genre.values().map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }
    )
    var genreError: MutableState<Boolean> = mutableStateOf(false)

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
            id = "$title + $year + $genres",
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

    private fun shouldEnableAddButton() {
        isEnabledAddButton.value =
            (titleError.value.not()
                    && yearError.value.not()
                    && directorError.value.not()
                    && actorsError.value.not()
                    && plotError.value.not()
                    && ratingError.value.not()
                    && genreError.value.not()
                    )
    }

    fun initValidate() {
        validateTitle()
        validateYear()
        validateDirector()
        validateActors()
        validatePlot()
        validateRating()
        validateGenres()
    }

    fun validateTitle() {
        titleError.value = title.value.isEmpty()
//        Log.d(TAG, "TitleError: ${titleError.value}") //For Debugging
        shouldEnableAddButton()
    }

    fun validateYear() {
        yearError.value = year.value.isEmpty()
//        Log.d(TAG, "YearError: ${yearError.value}") //For Debugging
        shouldEnableAddButton()
    }

    fun validateDirector() {
        directorError.value = director.value.isEmpty()
//        Log.d(TAG, "DirectorError: ${directorError.value}") //For Debugging
        shouldEnableAddButton()
    }

    fun validateActors() {
        actorsError.value = actors.value.isEmpty()
//        Log.d(TAG, "ActorsError: ${actorsError.value}") //For Debugging
        shouldEnableAddButton()
    }

    fun validatePlot() {
        plotError.value = plot.value.isEmpty()
//        Log.d(TAG, "PlotError: ${plotError.value}") //For Debugging
        shouldEnableAddButton()
    }

    fun validateRating() {
        try {
            rating.value.toFloat()
            ratingError.value = false
        } catch (e: java.lang.Exception) {
            ratingError.value = true
        } finally {
//            Log.d(TAG, "RatingError: ${ratingError.value}") //For Debugging
            shouldEnableAddButton()
        }
    }

    fun validateGenres() {
        genreError.value = true
        genreItems.value.forEach genres@{
            if (it.isSelected) {
                genreError.value = false
                return@genres
            }
        }
        shouldEnableAddButton()
    }
}