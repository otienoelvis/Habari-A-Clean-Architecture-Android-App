package com.elvisabc.habari.ui.repositories

import com.elvisabc.habari.data.datasources.NewsDataSource
import com.elvisabc.habari.data.entity.NewsResponse
import com.elvisabc.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    companion object{
        val TAG = NewsRepository::class.simpleName
    }
//    suspend fun getNewsHeadline(country: String) : Response<NewsResponse>{
//        return newsDataSource.getNewsHeadline(country)
//    }

    suspend fun getNewsHeadline(country: String) : Flow<ResourceState<NewsResponse>>{
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)

            if(response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            }
            else{
                emit(ResourceState.Error("Error fetching news"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "An error occurred"))
        }
    }
}