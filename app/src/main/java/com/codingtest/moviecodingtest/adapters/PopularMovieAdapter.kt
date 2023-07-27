package com.codingtest.moviecodingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.databinding.ViewHolderMovieBinding
import com.codingtest.moviecodingtest.databinding.ViewHolderPopularMovieBinding
import com.codingtest.moviecodingtest.delegate.MovieDelegate
import com.codingtest.moviecodingtest.viewholders.MovieViewHolder
import com.codingtest.moviecodingtest.viewholders.PopularMovieViewHolder

class PopularMovieAdapter(private val delegate: MovieDelegate) : RecyclerView.Adapter<PopularMovieViewHolder>() {
    private var mMovieList: List<MovieVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val itemBinding = ViewHolderPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieViewHolder(itemBinding,delegate)
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bindData(mMovieList[position])
    }

    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}