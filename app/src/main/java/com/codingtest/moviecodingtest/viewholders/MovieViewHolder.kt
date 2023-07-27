package com.codingtest.moviecodingtest.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingtest.moviecodingtest.R
import com.codingtest.moviecodingtest.constants.IMAGE_BASED_URL
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.databinding.ViewHolderMovieBinding
import com.codingtest.moviecodingtest.delegate.MovieDelegate


class MovieViewHolder(private val itemBinding: ViewHolderMovieBinding, mDelegate: MovieDelegate) : RecyclerView.ViewHolder(itemBinding.root) {
    private var mMovieVO : MovieVO? = null

    init {
        itemBinding.ivMovieCover.setOnClickListener{
            mMovieVO?.let {
                mDelegate.onTapMovie(it.id!!)
            }
        }
        itemBinding.layoutMovieDetail.setOnClickListener{
            mMovieVO?.let {
                mDelegate.toggleFavourite(it.id!!, it.isFavourite)
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        itemBinding.tvMovieName.text = movie.title
        Glide.with(itemView.context)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(itemBinding.ivMovieCover)

        if(movie.isFavourite){
            Glide.with(itemView.context)
                .load(R.drawable.ic_favourite_selected)
                .into(itemBinding.ivFavourite)
        }else{
            Glide.with(itemView.context)
                .load(R.drawable.ic_favourite)
                .into(itemBinding.ivFavourite)
        }

    }
}