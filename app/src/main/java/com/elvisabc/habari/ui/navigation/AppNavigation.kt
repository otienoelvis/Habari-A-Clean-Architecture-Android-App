package com.elvisabc.habari.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elvisabc.habari.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN){

        composable(Routes.HOME_SCREEN){
            HomeScreen(scrollBehavior = scrollBehavior)
        }

//        composable(Routes.HOME_SCREEN){
//            TopAppBarComponent()
//        }
    }

}