package winter.ld_02.modules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import winter.ld_02.navigation.Screen

@Composable
fun HomeScreenNavBar(navController: NavController) {
    var content = remember {
        mutableStateOf("Select menu to change content")
    }

    TopAppBar(
        title = { Text("Movie List") },
        actions = {
            TopAppBarDropdownMenu(content, navController)
        }
    )
}

@Composable
fun SimpleAppBar(titleText: String, navController: NavController) {
    TopAppBar(
        title = { Text(titleText) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun TopAppBarDropdownMenu(content: MutableState<String>, navController: NavController) {
    val expanded = remember { mutableStateOf(false) }

    Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
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

    DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            content.value = "Go to favroites"
        }) {
            Row(modifier = Modifier.clickable {
                navController.navigate(route = Screen.Favorites.route)
            }) {
                Icon(
                    Icons.Sharp.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
                Text("Favorites")
            }
        }
    }
}