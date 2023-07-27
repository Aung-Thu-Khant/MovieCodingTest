package com.codingtest.moviecodingtest.network


import com.codingtest.moviecodingtest.constants.API_GET_MOVIE_DETAIL
import com.codingtest.moviecodingtest.constants.API_GET_POPULAR_MOVIES
import com.codingtest.moviecodingtest.constants.API_GET_UPCOMING_MOVIES
import com.codingtest.moviecodingtest.constants.MOVIE_API_KEY
import com.codingtest.moviecodingtest.constants.PARAM_API_KEY
import com.codingtest.moviecodingtest.constants.PARAM_LANGUAGE
import com.codingtest.moviecodingtest.constants.PARAM_PAGE
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.network.responses.MovieListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_UPCOMING_MOVIES)
    fun getUpComingMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Observable<MovieListResponse>


    @GET(API_GET_MOVIE_DETAIL+"/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movie_id: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : Observable<MovieVO>

}