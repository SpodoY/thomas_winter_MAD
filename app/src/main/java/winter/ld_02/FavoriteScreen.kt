package winter.ld_02

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import winter.ld_02.modules.Screen
import winter.ld_02.modules.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {

    Column() {
        TopAppBar(
            title = { Text("Favorites") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        LazyColumn() {
            items(getMovies().subList(0, 2)) { movie ->
                MovieItem(movie, navController) { movieId ->
                    navController.navigate(route = Screen.MovieDetail.passId(movieId))
                }
            }
        }
    }

}
