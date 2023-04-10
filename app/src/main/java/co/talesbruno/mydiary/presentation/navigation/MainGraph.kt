package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.presentation.about.AboutScreen
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.perfil.PerfilScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

@Composable
fun MainGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
){
    NavHost(navController = navController, startDestination = "home", route = "main"){
        composable("home"){
            HomeScreen()
        }
        composable("about") {
            AboutScreen()
        }
        composable("perfil"){
            PerfilScreen(
                authViewModel = authViewModel
            )
        }
    }
}