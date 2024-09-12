package com.example.mvvmprac

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprac.ViewModel.MainActivityViewModel
import com.example.mvvmprac.adapter.MovieListAdapter
import com.example.mvvmprac.data.MovieModel

class MainActivity : AppCompatActivity()  , MovieListAdapter.OnItemClickListener {

    lateinit var recyclerAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        val movieListRecView = findViewById<RecyclerView>(R.id.movieListRecView)
        movieListRecView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = MovieListAdapter(this , this)
        movieListRecView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getLiveDataObserver().observe(this) { movieList ->
            if (movieList != null) {
                recyclerAdapter.setMovieList(movieList)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeApiCall()
    }

    override fun onItemClick(movie: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("overview", movie.overview)
        intent.putExtra("poster_path", movie.poster_path)
        startActivity(intent)
    }

}