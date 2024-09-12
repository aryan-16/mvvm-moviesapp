package com.example.mvvmprac.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmprac.R
import com.example.mvvmprac.data.MovieModel

class MovieListAdapter(private val activity: Activity , private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movieList: List<MovieModel> = listOf()


    fun setMovieList(movieList: List<MovieModel>?) {
        if (movieList != null) {
            this.movieList = movieList
        }
    }
    interface OnItemClickListener {
        fun onItemClick(movie: MovieModel)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: ImageView = view.findViewById(R.id.movieImageView)
        private val title: TextView = view.findViewById(R.id.titleTextView)
        private val description: TextView = view.findViewById(R.id.DescriptionTextView)

        fun bind(data: MovieModel) {
            title.text = data.title
            description.text = data.overview
            Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w500${data.poster_path}")
                .into(img)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movieList?.get(position)?.let { holder.bind(it) }

    }
}