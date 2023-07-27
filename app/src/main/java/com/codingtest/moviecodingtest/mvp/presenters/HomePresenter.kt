package com.codingtest.moviecodingtest.mvp.presenters

import com.codingtest.moviecodingtest.delegate.MovieDelegate
import com.codingtest.moviecodingtest.mvp.views.HomeView


interface HomePresenter : BasedPresenter<HomeView>, MovieDelegate {

}