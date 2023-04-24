package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.note.CreateNoteScreen
import co.talesbruno.mydiary.presentation.note.NoteDetailsScreen
import co.talesbruno.mydiary.presentation.note.UpdateNoteScreen
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@Composable
fun NoteGraph(
    navController: NavHostController = rememberNavController(),
    notes: List<Note>,
    noteViewModel: NoteViewModel
) {
    NavHost(navController = navController, startDestination = NotesScreens.Notes.route) {
        composable(route = NotesScreens.Notes.route) {
            HomeScreen(
                notes = notes,
                onNavigateToDetailScreen = { note ->
                    navController.navigate(
                        NotesScreens.NoteDetail.route + note.uuid
                    )
                },
                onNavigateToCreateNoteScreen = {
                    navController.navigate(NotesScreens.CreateNote.route)
                }
            )
        }
        composable(route = NotesScreens.CreateNote.route) {
            CreateNoteScreen(
                noteViewModel = noteViewModel,
                navController = navController
            )
        }
        composable(
            route = NotesScreens.UpdateNote.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId)
            val note = notes.find { note ->
                note.note == noteId
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
            route = NotesScreens.NoteDetail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId)
            val note = notes.find { note ->
                note.note == noteId
            }
            if (note != null) {
                NoteDetailsScreen(
                    noteViewModel = noteViewModel,
                    navController = navController,
                    note = note
                )
            }
        }
    }
}

sealed class NotesScreens(val route: String) {
    object Notes : NotesScreens(route = "notes")
    object CreateNote : NotesScreens(route = "createnote")
    object UpdateNote : NotesScreens(route = "updatenote/{noteId}")
    object NoteDetail : NotesScreens(route = "notedetail/{noteId}")
}
