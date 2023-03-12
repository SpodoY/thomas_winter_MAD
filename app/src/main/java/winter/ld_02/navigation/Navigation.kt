package winter.ld_02.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import winter.ld_02.screens.FavoriteScreen
import winter.ld_02.screens.HomeScreen
import winter.ld_02.screens.MovieScreen

@Composable
fun MyNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(navArgument(MOVIE_KEY) {
                type = NavType.StringType
            })
        ) {
//            Log.d("MovieItem", it.arguments?.getString(MOVIE_KEY).toString())
            MovieScreen(navController = navController, it.arguments?.getString(MOVIE_KEY).toString())
        }

        composable(
            route = Screen.Favorites.route,
        ) {
            FavoriteScreen(navController = navController)
        }
    }
}