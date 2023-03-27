package com.example.movieappmad23.data

data class FavoriteUiState (

    val allMovies: List<Movie> = getMovies(),
    val favoriteMovies: List<Movie> = listOf()
)
