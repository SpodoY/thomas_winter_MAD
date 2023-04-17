package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun addMovie(movie: Movie) = movieDao.add(movie)
    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)
    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAll()

    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavorites()

    fun getMovie(id: String): Flow<Movie?> = movieDao.getMovie(id)
}