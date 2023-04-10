package co.talesbruno.mydiary.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import co.talesbruno.mydiary.presentation.login.CreateAccount
import co.talesbruno.mydiary.presentation.login.LoginScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    navigation(startDestination = "signin", route = "auth") {
        composable("signin") {
            LoginScreen(
                onNavigateToCreateAccount = { navController.navigate("signup") },
                authViewModel = authViewModel,
                onNavigateToMainScreen = { navController.navigate("main") }
            )
        }
        composable("signup") {
            CreateAccount(
                authViewModel = authViewModel,
                onNavigateToMainScreen = { navController.navigate("main") }
            )
        }
    }
}