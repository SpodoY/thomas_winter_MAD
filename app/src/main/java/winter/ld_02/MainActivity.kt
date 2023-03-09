package winter.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import winter.ld_02.modules.Movie
import winter.ld_02.modules.getMovies
import winter.ld_02.ui.theme.LD_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LD_02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        TopBar()
                        MovieList(movieList = getMovies())
                    }
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {

    var isOpened by remember { mutableStateOf(Icons.Sharp.KeyboardArrowUp) }
    var isLiked by remember { mutableStateOf(Icons.Sharp.FavoriteBorder) }
    var isVisible by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(vertical = 20.dp)
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
fun MovieList(movieList: List<Movie>) {
    LazyColumn() {
        items(movieList) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun TopBar() {
    var content = remember {
        mutableStateOf("Select menu to change content")
    }

    TopAppBar(
        title = { Text("Movie List") },
        actions = {
            TopAppBarDropdownMenu(content)
        }
    )
}

@Composable
fun TopAppBarDropdownMenu(content: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
            content.value = "Menu Opening"
        }) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = null
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            content.value = "Go to favroites"
        }) {
            Row() {
                Icon (
                    Icons.Sharp.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
                Text("Favorites")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LD_02Theme {
        Column {
            TopBar()
            MovieList(movieList = getMovies())
        }
    }
}