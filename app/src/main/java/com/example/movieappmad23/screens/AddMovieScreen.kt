package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.ui.MoviesViewModel
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), moviesViewModel = moviesViewModel)
    }
    moviesViewModel.initValidate()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, moviesViewModel: MoviesViewModel) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            TextInputField(
                text = moviesViewModel.title,
                errorState = moviesViewModel.titleError,
                label = R.string.enter_movie_title,
                validateMethod = {moviesViewModel.validateTitle()}
            )

            TextInputField(
                text = moviesViewModel.year,
                errorState = moviesViewModel.yearError,
                label = R.string.enter_movie_year,
                validateMethod = {moviesViewModel.validateYear()}
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            DisplayErrorMsg(value = moviesViewModel.genreError.value)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(moviesViewModel.genreItems.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            moviesViewModel.genreItems.value = moviesViewModel.genreItems.value.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            moviesViewModel.validateGenres()
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            TextInputField(
                text = moviesViewModel.director,
                errorState = moviesViewModel.directorError,
                label = R.string.enter_director,
                validateMethod = {moviesViewModel.validateDirector()}
            )

            TextInputField(
                text = moviesViewModel.actors,
                errorState = moviesViewModel.actorsError,
                label = R.string.enter_actors,
                validateMethod = {moviesViewModel.validateActors()}
            )

            TextInputField(
                text = moviesViewModel.plot,
                errorState = moviesViewModel.plotError,
                label = R.string.enter_plot,
                validateMethod = {moviesViewModel.validatePlot()}
            )

            TextInputField(
                moviesViewModel.rating,
                moviesViewModel.ratingError,
                R.string.enter_rating,
                validateMethod = {moviesViewModel.validateRating()}
            )

            Button(
                enabled = moviesViewModel.isEnabledAddButton.value,
                onClick = {
                    val genreList: MutableList<Genre> = mutableListOf()
                    moviesViewModel.genreItems.value.filter { it.isSelected }.forEach { genreList.add(Genre.valueOf(it.title)) }

                    moviesViewModel.addMovie(
                        moviesViewModel.title.value,
                        moviesViewModel.year.value,
                        genreList,
                        moviesViewModel.director.value,
                        moviesViewModel.actors.value,
                        moviesViewModel.plot.value,
                        moviesViewModel.rating.value
                    )
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}

@Composable
private fun TextInputField(
    text: MutableState<String>,
    errorState: MutableState<Boolean>,
    label: Int,
    validateMethod: () -> Unit
) {
    OutlinedTextField(
        value = text.value,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            text.value = it
            validateMethod()
        },
        label = { Text(stringResource(id = label)) },
        isError = errorState.value
    )
    DisplayErrorMsg(value = errorState.value)
}

@Composable
private fun DisplayErrorMsg(value: Boolean) {
    if (value) {
        Text(
            text = stringResource(R.string.wrong_input),
            fontSize = 12.sp,
            color = Color.Red
        )
    }
}