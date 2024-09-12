package com.example.mvvmprac.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


   companion object{
       val BASE_URL = "https://api.themoviedb.org/3/"

       fun getRetroInstance(): Retrofit{
           return Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       }
   }
}