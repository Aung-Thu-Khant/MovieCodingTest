package com.codingtest.moviecodingtest.mvp.presenters

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.codingtest.moviecodingtest.data.model.MovieModelImpl
import com.codingtest.moviecodingtest.mvp.views.HomeView
import com.codingtest.moviecodingtest.mvp.views.MovieDetailView
import com.codingtest.moviecodingtest.persistence.MovieDatabase


class MovieDetailPresenterImpl : MovieDetailPresenter, AbstractBasedPresenter<MovieDetailView>() {

    var mMovieModel = MovieModelImpl
    var mMovieDatabase: MovieDatabase? = null
    override fun onUiReadyWithId(context: Context, owner: LifecycleOwner, id: Int) {
        mMovieDatabase = MovieDatabase.getDBInstant(context)
        val movie = mMovieDatabase?.movieDao()?.getOneTimeMovieById(id)

        movie?.let {
            mView.showMovieDetail(it)
        }
    }

    override fun onTapBack() {
        mView.navigateBack()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onTapMovie(id: Int) {

    }


    override fun toggleFavourite(id: Int, isFavourite: Boolean) {
        Log.d("toggle","$id -- $isFavourite")
        val movieVO = mMovieDatabase?.movieDao()?.getOneTimeMovieById(id)
        movieVO?.let { mMovieDatabase?.movieDao()?.insertSingleMovie(it.copy(isFavourite = !isFavourite)) }
    }


}