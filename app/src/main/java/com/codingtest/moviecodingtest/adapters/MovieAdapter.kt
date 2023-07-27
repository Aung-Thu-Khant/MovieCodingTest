package com.codingtest.moviecodingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.databinding.ViewHolderMovieBinding
import com.codingtest.moviecodingtest.delegate.MovieDelegate
import com.codingtest.moviecodingtest.viewholders.MovieViewHolder

class MovieAdapter(private val delegate: MovieDelegate) : RecyclerView.Adapter<MovieViewHolder>() {
    private var mMovieList: List<MovieVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding,delegate)
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(mMovieList[position])
    }

    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}