package co.talesbruno.mydiary

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.presentation.about.AboutScreen
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.login.CreateAccount
import co.talesbruno.mydiary.presentation.login.LoginScreen
import co.talesbruno.mydiary.presentation.login.LoginScreenPreview
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("about") {
            AboutScreen()
        }
    }
}

@Composable
fun LoginNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("signin") {
            LoginScreen(
                onNavigateToCreateAccount = { navController.navigate("signup") },
                authViewModel = authViewModel
            )
        }
        composable("signup") {
            CreateAccount()
        }
    }
}