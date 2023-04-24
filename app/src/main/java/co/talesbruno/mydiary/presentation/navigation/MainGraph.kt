package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.presentation.about.AboutScreen
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.perfil.ProfileScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@Composable
fun MainGraph(
    navController: NavHostController,
    notes: List<Note>,
    user: User,
    noteViewModel: NoteViewModel,
    authViewModel: AuthViewModel,
    onNavigateToLoginScreen: () -> Unit,
) {
    NavHost(navController = navController, startDestination = MainScreens.Home.route) {
        composable(route = MainScreens.Home.route) {
            NoteGraph(
                notes = notes,
                noteViewModel = noteViewModel
            )
        }
        composable(route = MainScreens.About.route) {
            AboutScreen()
        }
        composable(route = MainScreens.Profile.route) {
            ProfileScreen(
                user = user,
                onNavigateToLoginScreen = onNavigateToLoginScreen,
                authViewModel = authViewModel
            )
        }
    }
}



sealed class MainScreens(val route: String) {
    object Home : MainScreens(route = "home")
    object About : MainScreens(route = "about")
    object Profile : MainScreens(route = "profile")
}