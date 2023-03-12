package winter.ld_02

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import winter.ld_02.modules.Screen.MovieDetail.getMovieFromId

@Composable
fun MovieScreen(navController: NavController, movieid: String) {

    val selectedMovie = getMovieFromId(id = movieid)

    Column {
        TopAppBar(
            title = { Text(selectedMovie.title) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        MovieItem(movie = selectedMovie, navController = navController)
        MovieImages(movieList = selectedMovie.images)


    }
}

@Composable
fun MovieImages(movieList: List<String>) {
    Box() {
        Column() {
            Text("Movie Images",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            LazyRow(
                Modifier.height(300.dp)
            ) {
                items(movieList) {
                    val painter = rememberAsyncImagePainter(model = it)
                    Image(
                        painter = painter,
                        contentDescription = "Movie Picture",
                        modifier = Modifier
                            .width(350.dp) //If you remove this, it breaks... idk why, idk how but it does
                            .fillMaxHeight()
                            .padding(horizontal = 15.dp),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }
    }
}
