package com.elvisabc.habari.data.datasources

import com.elvisabc.habari.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadline(country: String): Response<NewsResponse>

}