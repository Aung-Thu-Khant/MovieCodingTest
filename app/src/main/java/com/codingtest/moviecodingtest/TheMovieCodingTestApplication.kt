package com.codingtest.moviecodingtest

import android.app.Application
import com.codingtest.moviecodingtest.data.model.MovieModelImpl

class TheMovieCodingTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}