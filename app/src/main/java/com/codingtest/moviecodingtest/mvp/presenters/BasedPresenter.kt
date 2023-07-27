package com.codingtest.moviecodingtest.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.codingtest.moviecodingtest.mvp.views.BasedView

interface BasedPresenter<V : BasedView>{
    fun initView(view: V)
    fun onUiReady(context: Context, owner: LifecycleOwner)
}