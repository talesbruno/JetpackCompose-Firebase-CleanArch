package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.presentation.main.MainScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

@Composable
fun RootNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel
){
    NavHost(navController = navController, startDestination = "auth", route = "root"){
        authGraph(
            navController,
            authViewModel,
        )
        composable("main"){
            MainScreen(
                navController = navController,
                authViewModel = authViewModel,
            )
        }
    }
}