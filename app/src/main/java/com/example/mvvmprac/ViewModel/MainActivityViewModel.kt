package com.example.mvvmprac.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmprac.data.MovieModel
import com.example.mvvmprac.data.MovieResponse
import com.example.mvvmprac.retrofit.RetroServiceInterface
import com.example.mvvmprac.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    val liveDataList: MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<MovieModel>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retroInstance = RetrofitInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getMovieList("38a73d59546aa378980a88b645f487fc")
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body()?.results)
                } else {
                    liveDataList.postValue(null)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                liveDataList.postValue(null)

            }
        })
    }
}