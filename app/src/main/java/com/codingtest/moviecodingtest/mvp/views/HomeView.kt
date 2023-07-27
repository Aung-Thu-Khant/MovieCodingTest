package com.codingtest.moviecodingtest.mvp.views

import com.codingtest.moviecodingtest.data.vo.MovieVO

interface HomeView: BasedView {
    fun navigateToMovieDetailScreen(id: Int)
    fun showLoading()
    fun showPopularMovies(movieList: List<MovieVO>)
    fun showUpcomingMovies(movieList: List<MovieVO>)
}