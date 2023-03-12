package winter.ld_02.modules

const val MOVIE_KEY = "id"

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Movie: Screen(route = "movie_screen/{$MOVIE_KEY}") {

        fun passId(id: String): String {
            return this.route.replace("{$MOVIE_KEY}", id)
        }
    }
}
