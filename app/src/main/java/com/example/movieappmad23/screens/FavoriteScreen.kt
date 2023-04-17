package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad23.viewmodels.FavoritesViewModel
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController, favViewModel: FavoritesViewModel) {

    val favoriteMoviesState by favViewModel.favoriteMoviesState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(favoriteMoviesState) { movie ->
                    MovieRow(
                        movie = movie,
                        onMovieRowClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        },
                        onFavClick = { likedMovie ->
                            coroutineScope.launch {
                                favViewModel.updateFavoriteMovies(likedMovie)
                            }
                        }
                    )
                }
            }
        }
    }
}