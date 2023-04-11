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
) {
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.AUTH) {
        authGraph(
            navController,
            authViewModel,
        )
        composable(route = Graph.MAIN) {
            MainScreen(
                authViewModel = authViewModel,
            )
        }
    }
}

object Graph{
    const val ROOT = "root"
    const val AUTH = "auth"
    const val MAIN = "main"
}