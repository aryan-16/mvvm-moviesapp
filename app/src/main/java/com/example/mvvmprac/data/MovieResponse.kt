package com.example.mvvmprac.data

data class MovieResponse(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)
