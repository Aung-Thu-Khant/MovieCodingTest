package com.codingtest.moviecodingtest.activities

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.codingtest.moviecodingtest.R
import com.codingtest.moviecodingtest.adapters.MovieAdapter
import com.codingtest.moviecodingtest.adapters.PopularMovieAdapter
import com.codingtest.moviecodingtest.adapters.ViewPagerAdapter
import com.codingtest.moviecodingtest.data.vo.MovieVO
import com.codingtest.moviecodingtest.databinding.ActivityHomeBinding
import com.codingtest.moviecodingtest.mvp.presenters.HomePresenterImpl
import com.codingtest.moviecodingtest.mvp.views.HomeView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity(), HomeView{
    lateinit var binding: ActivityHomeBinding
    lateinit var mPopularMovieAdapter: PopularMovieAdapter
    lateinit var mUpcomingMovieAdapter: MovieAdapter

    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var indicator : LinearLayout

    val viewModel by viewModels<HomePresenterImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        indicator = findViewById(R.id.indicator_layout)

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.slideVp.adapter = viewPagerAdapter

        binding.slideVp.addOnPageChangeListener(viewListener)

        setUpindicator(0)

        setUpRecyclerView()
        setUpTabLayout()

        viewModel.initView(this)
        viewModel.onUiReady(this, this)


    }

    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            setUpindicator(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }


    fun setUpindicator(position: Int) {

        var dots = arrayOfNulls<TextView>(4)
        indicator.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#8226"))
            dots[i]?.setTextSize(35f)
            dots[i]?.setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            indicator.addView(dots.get(i))
        }
        dots.get(position)?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private fun setUpRecyclerView(){
        mUpcomingMovieAdapter = MovieAdapter(viewModel)
        binding.rvUpcomingMovie.adapter = mUpcomingMovieAdapter

        mPopularMovieAdapter = PopularMovieAdapter(viewModel)
        binding.rvPopularMovie.adapter = mPopularMovieAdapter
    }

    private fun setUpTabLayout(){
        listOf("Movies","Events","Plays","Sports","Activities").forEach { genre ->
            binding.tabLayoutGeneres.newTab().apply {
                text = genre
                binding.tabLayoutGeneres.addTab(this)
            }
        }
    }

    override fun navigateToMovieDetailScreen(id: Int) {
        startActivity(MovieDetailActivity.newIntent(this,id))
    }

    override fun showLoading() {
       Snackbar.make(window.decorView,"Loading",Snackbar.LENGTH_SHORT).show()
    }

    override fun showPopularMovies(movieList: List<MovieVO>) {
        mPopularMovieAdapter.setNewData(movieList)
    }

    override fun showUpcomingMovies(movieList: List<MovieVO>) {
        mUpcomingMovieAdapter.setNewData(movieList)

    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }
}