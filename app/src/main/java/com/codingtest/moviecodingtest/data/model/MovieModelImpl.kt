package com.codingtest.moviecodingtest.data.model

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.codingtest.moviecodingtest.constants.POPULAR
import com.codingtest.moviecodingtest.constants.UPCOMING
import com.codingtest.moviecodingtest.data.vo.MovieVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BasedModel(), MovieModel {

    override fun getUpcomingMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {

        mTheMovieApi.getUpComingMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = UPCOMING
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(UPCOMING)

    }

    override fun getPopularMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {

        mTheMovieApi.getPopularMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = POPULAR
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(POPULAR)

    }



    @SuppressLint("CheckResult")
    override fun getMovieDetailById(
        id: String,
        onFail: (String) -> Unit
    ): LiveData<MovieVO?>? {


        mTheMovieApi.getMovieById(movie_id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val movieFromDBToSync: MovieVO? =
                    mMovieDatabase?.movieDao()?.getOneTimeMovieById(id.toInt())

                    response.type = movieFromDBToSync?.type.toString()
                    mMovieDatabase?.movieDao()?.insertSingleMovie(response)

            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMovieById(id.toInt())
    }
}