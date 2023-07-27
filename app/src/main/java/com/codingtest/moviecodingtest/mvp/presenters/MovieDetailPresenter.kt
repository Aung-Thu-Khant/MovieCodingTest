package com.codingtest.moviecodingtest.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.codingtest.moviecodingtest.delegate.MovieDelegate
import com.codingtest.moviecodingtest.mvp.views.HomeView
import com.codingtest.moviecodingtest.mvp.views.MovieDetailView


interface MovieDetailPresenter : BasedPresenter<MovieDetailView>, MovieDelegate {
    fun onUiReadyWithId(context: Context, owner: LifecycleOwner, id: Int)
    fun onTapBack()
}