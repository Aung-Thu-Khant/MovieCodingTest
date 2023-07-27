package com.codingtest.moviecodingtest.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingtest.moviecodingtest.data.vo.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieVO>>


    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int) : LiveData<MovieVO?>?

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getOneTimeMovieById(id: Int) : MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type: String) : LiveData<List<MovieVO>>
}