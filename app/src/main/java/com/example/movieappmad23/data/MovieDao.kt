package com.example.movieappmad23.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun add(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun getMovie(id: String): Flow<Movie?>
}