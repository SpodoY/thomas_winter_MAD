package winter.ld_02

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MovieScreen(navController: NavController, movieid: String) {

    Text("This movie has the ID of $movieid")
    
}