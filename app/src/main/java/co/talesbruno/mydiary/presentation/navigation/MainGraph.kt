package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.presentation.about.AboutScreen
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.note.CreateNoteScreen
import co.talesbruno.mydiary.presentation.note.NoteDetailsScreen
import co.talesbruno.mydiary.presentation.note.UpdateNoteScreen
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
            HomeScreen(
                notes = notes,
                onNavigateToCreateNoteScreen = {
                    navController.navigate(MainScreens.CreateNote.route)
                },
                onNavigateToDetailScreen = { noteId ->
                    navController.navigate(MainScreens.NoteDetail.createRoute(noteId.uuid.toString()))
                },
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
        composable(route = MainScreens.CreateNote.route) {
            CreateNoteScreen(
                noteViewModel = noteViewModel,
                navController = navController
            )
        }
        composable(
            route = MainScreens.UpdateNote.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId)
            val note = notes.find { note ->
                note.uuid == noteId
            }
            if (note != null) {
                UpdateNoteScreen(
                    noteViewModel = noteViewModel,
                    navController = navController,
                    note = note
                )
            }
        }
        composable(
            route = MainScreens.NoteDetail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId)
            val note = notes.find { note ->
                note.uuid == noteId
            }
            if (note != null) {
                NoteDetailsScreen(
                    noteViewModel = noteViewModel,
                    navController = navController,
                    note = note,
                    onNavigateToUpdateScreen = { noteId ->
                        navController.navigate(MainScreens.UpdateNote.createRoute(noteId.uuid.toString()))
                    }
                )
            }
        }
    }
}



sealed class MainScreens(val route: String) {
    object Home : MainScreens(route = "home")
    object About : MainScreens(route = "about")
    object Profile : MainScreens(route = "profile")
    object CreateNote : MainScreens(route = "createnote")
    object UpdateNote : MainScreens(route = "updatenote/{noteId}"){
        fun createRoute(noteId: String) = "updatenote/$noteId"
    }
    object NoteDetail : MainScreens(route = "notedetail/{noteId}"){
        fun createRoute(noteId: String) = "notedetail/$noteId"
    }
}