@file:OptIn(ExperimentalMaterial3Api::class)

package com.elvisabc.habari.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elvisabc.habari.data.entity.NewsResponse
import com.elvisabc.habari.ui.components.EmptyStateComponent
import com.elvisabc.habari.ui.components.Loader
import com.elvisabc.habari.ui.components.NewsRowComponent
import com.elvisabc.habari.ui.components.TopAppBarComponent
import com.elvisabc.habari.ui.viewmodel.NewsViewModel
import com.elvisabc.utilities.ResourceState

const val TAG = "HomeScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
//    paddingValues: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val newsResponse by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){

        newsResponse.let {
            if (it is ResourceState.Success) {
                it.data.articles?.size
            } else {
                1
            }
        } ?: 1
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarComponent(scrollBehavior)
        }
    ) { values ->
        VerticalPager(
            beyondBoundsPageCount = 2,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(values),
            pageSize = PageSize.Fill,
            pageSpacing = 8.dp
        ) { page:Int ->

            when(newsResponse){
                is ResourceState.Loading ->{
                    Log.d(TAG, "Inside_Loading")
                    Loader()
                }

                is ResourceState.Success ->{
                    Log.d(TAG, "Inside_Success")
                    val response = (newsResponse as ResourceState.Success).data
                    Log.d(TAG, "Inside_Success ${response.totalResults}")

                    if(!response.articles.isNullOrEmpty()){
                        NewsRowComponent(page, response.articles[page])
                    }else{
                        EmptyStateComponent()
                    }

                }

                is ResourceState.Error ->{
                    Log.d(TAG, "Inside_Error")
                    Log.d(TAG, (newsResponse as ResourceState.Error<NewsResponse>).error.toString())
                    EmptyStateComponent()
                }

            }
        }
    }

//    VerticalPager(
//        beyondBoundsPageCount = 2,
//        state = pagerState,
//        modifier = Modifier
//            .fillMaxSize(),
//        pageSize = PageSize.Fill,
//        pageSpacing = 8.dp
//    ) { page:Int ->
//
//        when(newsResponse){
//            is ResourceState.Loading ->{
//                Log.d(TAG, "Inside_Loading")
//                Loader()
//            }
//
//            is ResourceState.Success ->{
//                Log.d(TAG, "Inside_Success")
//                val response = (newsResponse as ResourceState.Success).data
//                Log.d(TAG, "Inside_Success ${response.totalResults}")
//
//                if(!response.articles.isNullOrEmpty()){
//                    NewsRowComponent(page, response.articles[page])
//                }else{
//                    EmptyStateComponent()
//                }
//
//            }
//
//            is ResourceState.Error ->{
//                Log.d(TAG, "Inside_Error")
//                Log.d(TAG, (newsResponse as ResourceState.Error<NewsResponse>).error.toString())
//                EmptyStateComponent()
//            }
//
//        }
//    }

}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}