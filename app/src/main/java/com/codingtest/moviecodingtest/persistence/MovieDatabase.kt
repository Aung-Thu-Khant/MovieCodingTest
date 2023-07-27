package com.codingtest.moviecodingtest.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.persistence.daos.MovieDao

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "THE_MOVIE_DATABASE"
        var dbInstant : MovieDatabase? = null

        fun getDBInstant(context: Context) : MovieDatabase?{
            when(dbInstant){
                null -> {
                    dbInstant = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstant
        }
    }

    abstract fun movieDao() : MovieDao
}