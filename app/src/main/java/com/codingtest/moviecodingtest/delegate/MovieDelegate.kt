package com.codingtest.moviecodingtest.delegate

interface MovieDelegate {
    fun onTapMovie(id: Int)
    fun toggleFavourite(id: Int, isFavourite: Boolean)
}