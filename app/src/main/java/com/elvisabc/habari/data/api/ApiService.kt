package com.elvisabc.habari.data.api

import com.elvisabc.habari.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("ddv2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "0e4cd5dbfc4244c68b77fa7e0ccf8a66"
    ) : Response<NewsResponse>
}

//https://newsapi.org/v2/top-headlines?country=us&apiKey=0e4cd5dbfc4244c68b77fa7e0ccf8a66