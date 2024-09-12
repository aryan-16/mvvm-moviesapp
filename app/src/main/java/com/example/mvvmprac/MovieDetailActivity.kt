package com.example.mvvmprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val posterPath = intent.getStringExtra("poster_path")

        val overviewDetail = findViewById<TextView>(R.id.overviewDetail)
        val titleTextView = findViewById<TextView>(R.id.titleDetail)
        val posterImageView = findViewById<ImageView>(R.id.imgDetail)

        titleTextView.text = title
        overviewDetail.text = overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(posterImageView)
    }
}
