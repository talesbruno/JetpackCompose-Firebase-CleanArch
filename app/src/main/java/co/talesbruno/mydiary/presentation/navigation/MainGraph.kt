package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    authViewModel: AuthViewModel,
    notes: List<Note>,
    user: User,
    noteViewModel: NoteViewModel
) {
//    val notes by noteViewModel.notes.collectAsStateWithLifecycle()
//    val user by authViewModel.auth.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = MainScreens.Home.route) {
        composable(route = MainScreens.Home.route) {
            HomeScreen(notes)
        }
        composable(route = MainScreens.About.route) {
            AboutScreen()
        }
        composable(route = MainScreens.Profile.route) {
            ProfileScreen(
                user = user
            )
        }
    }
}


sealed class MainScreens(val route: String) {
    object Home : MainScreens(route = "home")
    object About : MainScreens(route = "about")
    object Profile : MainScreens(route = "profile")
}