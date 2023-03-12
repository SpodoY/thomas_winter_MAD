package winter.ld_02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import winter.ld_02.modules.MovieCarousel
import winter.ld_02.modules.MovieItem
import winter.ld_02.modules.SimpleAppBar
import winter.ld_02.navigation.Screen.MovieDetail.getMovieFromId

@Composable
fun MovieScreen(navController: NavController, movieid: String) {

    val selectedMovie = getMovieFromId(id = movieid)

    Column {
        SimpleAppBar(titleText = selectedMovie.title, navController = navController)
        MovieItem(movie = selectedMovie)
        MovieCarousel(movieList = selectedMovie.images)
    }
}
