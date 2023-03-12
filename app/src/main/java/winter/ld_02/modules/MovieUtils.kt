package winter.ld_02.modules

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import winter.ld_02.navigation.Screen



@Composable
fun MovieList(movieList: List<Movie>, navController: NavController, ) {
    LazyColumn() {
        items(movieList) { movie ->
            MovieItem(movie) { movieId ->
                navController.navigate(route = Screen.MovieDetail.passId(movieId))
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var isOpened by remember { mutableStateOf(Icons.Sharp.KeyboardArrowUp) }
    var isLiked by remember { mutableStateOf(Icons.Sharp.FavoriteBorder) }
    var isVisible by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(vertical = 20.dp)
            .clickable {
                onItemClick(movie.id)
            }
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .height(150.dp)
            ) {
                val painter = rememberAsyncImagePainter(model = movie.images[1])
                Image(
                    painter = painter,
                    contentDescription = "Test Bild",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Icon(
                    isLiked,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            isLiked = if (isLiked == Icons.Sharp.FavoriteBorder)
                                Icons.Filled.Favorite
                            else Icons.Sharp.FavoriteBorder
                        }
                )
            }
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .height(35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = isOpened,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            isOpened = if (isOpened == Icons.Sharp.KeyboardArrowUp)
                                Icons.Sharp.KeyboardArrowDown
                            else Icons.Sharp.KeyboardArrowUp
                            isVisible = !isVisible
                        }
                )
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { -40 }
                ) + expandVertically(
                    expandFrom = Alignment.Top
                ),
                exit = slideOutVertically() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Director: ${movie.director}")
                    Text("Released: ${movie.year}")
                    Text("Genre: ${movie.genre}")
                    Text("Actors: ${movie.actors}")
                    Text("Rating: ${movie.rating}")
                    Divider(thickness = 2.dp)
                    Text("Plot: ${movie.plot}")
                }
            }
        }
    }
}

@Composable
fun MovieCarousel(movieList: List<String>) {
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