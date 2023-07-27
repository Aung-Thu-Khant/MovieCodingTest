package com.codingtest.moviecodingtest.data.model

import androidx.lifecycle.LiveData
import com.codingtest.moviecodingtest.data.vo.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {

     fun getUpcomingMovies(
        onFail : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
        onFail : (String) -> Unit
    ): LiveData<List<MovieVO>>?


    fun getMovieDetailById(
        id: String,
        onFail : (String) -> Unit
    ): LiveData<MovieVO?>?

}