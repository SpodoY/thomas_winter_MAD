package winter.ld_02.modules

const val MOVIE_KEY = "id"

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object MovieDetail: Screen(route = "movie_screen/{$MOVIE_KEY}") {

        fun passId(id: String): String {
            return this.route.replace("{$MOVIE_KEY}", id)
        }

        fun getMovieFromId(id: String): Movie {
            return getMovies().filter { it.id == id }[0]
        }
    }

    object Favorites: Screen(route = "favorites")
}
