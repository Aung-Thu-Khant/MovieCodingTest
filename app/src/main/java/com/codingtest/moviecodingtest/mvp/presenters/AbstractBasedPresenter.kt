package com.codingtest.moviecodingtest.mvp.presenters

import androidx.lifecycle.ViewModel
import com.codingtest.moviecodingtest.mvp.views.BasedView

abstract class AbstractBasedPresenter<V : BasedView> : BasedPresenter<V>, ViewModel(){
    protected lateinit var mView: V

    override fun initView(view: V) {
        mView = view
    }
}