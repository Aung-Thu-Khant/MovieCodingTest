package com.codingtest.moviecodingtest.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import com.bumptech.glide.Glide
import com.codingtest.moviecodingtest.R
import com.codingtest.moviecodingtest.constants.IMAGE_BASED_URL
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.databinding.ActivityMovieDetailBinding
import com.codingtest.moviecodingtest.mvp.presenters.MovieDetailPresenterImpl
import com.codingtest.moviecodingtest.mvp.views.MovieDetailView
import com.google.android.material.snackbar.Snackbar

val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
class MovieDetailActivity : AppCompatActivity(), MovieDetailView{

    lateinit var binding: ActivityMovieDetailBinding
    val viewModel by viewModels<MovieDetailPresenterImpl>()
    companion object {
        fun newIntent(context: Context, movieId: Int?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
        intent?.getIntExtra(EXTRA_MOVIE_ID, 0)?.let {
            initViewModel(it)
        }

    }

    private fun setUpListeners(){
        binding.btnBack.setOnClickListener {
            viewModel.onTapBack()
        }
    }

    private fun initViewModel(id: Int){
        viewModel.initView(this)
        viewModel.onUiReadyWithId(this, this, id)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showMovieDetail(movie: MovieVO) {
        Glide.with(this)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(binding.ivDetailCover)

        if(!movie.isFavourite){
            Glide.with(this)
                .load(R.drawable.ic_favourite)
                .into(binding.ivFavourite)
        }else{
            Glide.with(this)
                .load(R.drawable.ic_favourite_selected)
                .into(binding.ivFavourite)
        }

        binding.tvDetailName.text = movie.title

      //  binding.tvDate.text = movie.

        binding.detailFavouriteLayout.setOnClickListener {
            if(movie.isFavourite){
                Glide.with(this)
                    .load(R.drawable.ic_favourite)
                    .into(binding.ivFavourite)
            }else{
                Glide.with(this)
                    .load(R.drawable.ic_favourite_selected)
                    .into(binding.ivFavourite)
            }
            viewModel.toggleFavourite(movie.id!!, movie.isFavourite)
        }
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}