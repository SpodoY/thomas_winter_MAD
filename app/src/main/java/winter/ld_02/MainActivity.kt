package winter.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    MovieList(movieList = getMovies())
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {

    var isOpened by remember { mutableStateOf(Icons.Sharp.KeyboardArrowUp) }
    var isLiked by remember { mutableStateOf(Icons.Sharp.FavoriteBorder) }

//    var isOpenend = mutableStateOf(Icons.Sharp.KeyboardArrowDown)

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cherryblossom),
                    contentDescription = "Test Bild",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Icon (
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
                Text(text = movie.title, fontSize = 20.sp)
                Icon(
                    imageVector = isOpened,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            isOpened = if (isOpened == Icons.Sharp.KeyboardArrowUp)
                                Icons.Sharp.KeyboardArrowDown
                                else Icons.Sharp.KeyboardArrowUp
                        }
                )
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
    TopAppBar(
        title = {
            Text("Movie List")
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon (
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
    )
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