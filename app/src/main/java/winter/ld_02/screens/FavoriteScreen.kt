package winter.ld_02.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import winter.ld_02.modules.MovieList
import winter.ld_02.modules.SimpleAppBar
import winter.ld_02.modules.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {

    Column() {
        SimpleAppBar(titleText = "Favorites", navController = navController)
        MovieList(movieList = getMovies().subList(0, 2), navController = navController)
    }
}
