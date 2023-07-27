package com.codingtest.moviecodingtest.mvp.presenters

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.codingtest.moviecodingtest.data.model.MovieModelImpl
import com.codingtest.moviecodingtest.mvp.views.HomeView
import com.codingtest.moviecodingtest.persistence.MovieDatabase


class HomePresenterImpl : HomePresenter, AbstractBasedPresenter<HomeView>() {

    var mMovieModel = MovieModelImpl
    var mMovieDatabase: MovieDatabase? = null

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mMovieDatabase = MovieDatabase.getDBInstant(context)
        mView.showLoading()

        mMovieModel.getPopularMovies {
            mView.showErrorMessage(it)
        }?.observe(owner){
            mView.showPopularMovies(it)
        }

        mMovieModel.getUpcomingMovies {
            mView.showErrorMessage(it)
        }?.observe(owner){
            mView.showUpcomingMovies(it)
        }
    }

    override fun onTapMovie(id: Int) {
        mView.navigateToMovieDetailScreen(id)
    }

    override fun toggleFavourite(id: Int, isFavourite: Boolean) {
        Log.d("toggle","$id -- $isFavourite")
        val movieVO = mMovieDatabase?.movieDao()?.getOneTimeMovieById(id)
        movieVO?.let { mMovieDatabase?.movieDao()?.insertSingleMovie(it.copy(isFavourite = !isFavourite)) }
    }


}