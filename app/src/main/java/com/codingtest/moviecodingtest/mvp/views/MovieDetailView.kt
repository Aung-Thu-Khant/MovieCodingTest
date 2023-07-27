package com.codingtest.moviecodingtest.mvp.views

import com.codingtest.moviecodingtest.data.vo.MovieVO

interface MovieDetailView: BasedView {
    fun navigateBack()
    fun showMovieDetail(movie: MovieVO)
}