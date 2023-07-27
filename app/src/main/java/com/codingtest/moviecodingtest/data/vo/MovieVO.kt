package com.codingtest.moviecodingtest.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieVO(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "is_favourite")
    @SerializedName("is_favourite")
    val isFavourite: Boolean = false,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String = "",
)
