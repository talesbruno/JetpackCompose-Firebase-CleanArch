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
    navigation(route = Graph.AUTH, startDestination = AuthScreen.SignIn.route) {
        composable(route = AuthScreen.SignIn.route) {
            LoginScreen(
                onNavigateToCreateAccount = { navController.navigate(route = AuthScreen.SignUp.route) },
                authViewModel = authViewModel,
                onNavigateToMainScreen = { navController.navigate(route = Graph.MAIN) }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            CreateAccount(
                authViewModel = authViewModel,
                onNavigateToMainScreen = { navController.navigate(route = Graph.MAIN) }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object SignIn : AuthScreen(route = "signin")
    object SignUp : AuthScreen(route = "signup")
}