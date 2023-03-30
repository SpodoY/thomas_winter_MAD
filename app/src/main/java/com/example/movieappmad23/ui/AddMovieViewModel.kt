package com.example.movieappmad23.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.data.AddMovieData

class AddMovieViewModel : ViewModel() {

    var addMovieData: AddMovieData = AddMovieData()

    var title = mutableStateOf(addMovieData.title)
    var year = mutableStateOf(addMovieData.year)
    var genres = mutableStateOf(addMovieData.genres)
    var director = mutableStateOf(addMovieData.director)
    var actors = mutableStateOf(addMovieData.actors)
    var plot = mutableStateOf(addMovieData.plot)
    var rating = mutableStateOf(addMovieData.rating)
    var isButtonEnabled = mutableStateOf(false)

    init { }

    private fun isButtonEnabled() {
        isButtonEnabled.value =
            title.value.isEmpty()
            && year.value.isEmpty()
            && genres.value.isEmpty()
            && director.value.isEmpty()
            && actors.value.isEmpty()
            && plot.value.isEmpty()
            && rating.value != 0.0f
    }

    fun validateTitle() {
                
    }

}
