package com.codingtest.moviecodingtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.codingtest.moviecodingtest.R
import com.google.android.material.imageview.ShapeableImageView

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
   // var context: Context? = null

    var images = intArrayOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3
    )


    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.slide_show_ad, container, false)
        val slidetitleimage = view.findViewById<View>(R.id.ivMovieCover) as ShapeableImageView
        slidetitleimage.setImageResource(images[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}