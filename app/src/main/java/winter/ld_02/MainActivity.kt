package winter.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    MovieRow()
                }
            }
        }
    }
}

@Composable
fun MovieRow() {
    Card(
        shape = RoundedCornerShape(15.dp),
    ) {
        Column() {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.cherryblossom),
                    contentDescription = "Test Bild",
                    modifier = Modifier.fillMaxWidth()
                )
                Icon(
                    Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd)
                        .clickable { println("clicked") }
                )
            }
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Avatar 2")
                Icon (
                    Icons.Sharp.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LD_02Theme {
//        Greeting("Android")
        MovieRow()
    }
}