package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.presentation.home.NotesScreen
import co.talesbruno.mydiary.presentation.note.CreateNoteScreen
import co.talesbruno.mydiary.presentation.note.UpdateNoteScreen
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@Composable
fun NoteGraph(
    navController: NavHostController,
    notes: List<Note>,
    noteViewModel: NoteViewModel
) {
    NavHost(navController = navController, startDestination = MainScreens.Home.route) {
        composable(route = NotesScreens.Notes.route){
            NotesScreen(notes = notes)
        }
        composable(route = NotesScreens.CreateNote.route) {
            CreateNoteScreen(
                noteViewModel = noteViewModel,
                navController = navController
            )
        }
        composable(route = NotesScreens.UpdateNote.route) {
            UpdateNoteScreen(
                noteViewModel = noteViewModel,
                navController = navController,
            )
        }
    }
}

sealed class NotesScreens(val route: String) {
    object Notes : NotesScreens(route = "notes")
    object CreateNote : NotesScreens(route = "createnote")
    object UpdateNote : NotesScreens(route = "updatenote")
    object NoteDetail : NotesScreens(route = "notedetail")
}
