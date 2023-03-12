package winter.ld_02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import winter.ld_02.modules.HomeScreenNavBar
import winter.ld_02.modules.MovieList
import winter.ld_02.modules.getMovies

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column {
        HomeScreenNavBar(navController)
        MovieList(movieList = getMovies(), navController = navController)
    }
}