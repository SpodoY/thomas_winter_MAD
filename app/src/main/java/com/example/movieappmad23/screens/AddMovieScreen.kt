package com.example.movieappmad23.screens

import android.util.Log
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
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

            val genres = Genre.values().toList()
            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            OutlinedTextField(
                value = moviesViewModel.title.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { moviesViewModel.title.value = it },
                label = { Text(stringResource(R.string.enter_movie_title)) },
                isError = moviesViewModel.stringNotEmpty(moviesViewModel.title.value)
            )

            OutlinedTextField(
                value = moviesViewModel.year.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { moviesViewModel.year.value = it },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = moviesViewModel.stringNotEmpty(moviesViewModel.year.value)
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            OutlinedTextField(
                value = moviesViewModel.director.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { moviesViewModel.director.value = it },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = moviesViewModel.stringNotEmpty(moviesViewModel.director.value)
            )

            OutlinedTextField(
                value = moviesViewModel.actors.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { moviesViewModel.actors.value = it },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = moviesViewModel.stringNotEmpty(moviesViewModel.actors.value)
            )

            OutlinedTextField(
                value = moviesViewModel.plot.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { moviesViewModel.plot.value = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = moviesViewModel.stringNotEmpty(moviesViewModel.plot.value)
            )

            OutlinedTextField(
                value = moviesViewModel.rating.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                            moviesViewModel.rating.value = if(it.startsWith("0")) {
                                    ""
                                } else {
                                    it
                                }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = moviesViewModel.floatNotEmpty(moviesViewModel.rating.value)
            )

            Button(
                enabled = moviesViewModel.isEnabledSaveButton.value, //TODO: Disable as long as input is wrong
                onClick = {
                    val genreList: MutableList<Genre> = mutableListOf()
                    genreItems.filter { it.isSelected }.forEach { genreList.add(Genre.valueOf(it.title)) }

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
fun displayLabelText(errorState: Boolean, successTxt: String) {
    if (errorState) Text(stringResource(R.string.wrong_input)) else Text(successTxt)
}