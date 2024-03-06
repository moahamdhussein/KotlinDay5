package com.example.kotlinday5.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitHelper {
    private const val BASE_URL = "https://dummyjson.com/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
object API{
    val retrofitService :ApiService by lazy {
        RetrofitHelper.getInstance().create(ApiService::class.java)
    }
}
