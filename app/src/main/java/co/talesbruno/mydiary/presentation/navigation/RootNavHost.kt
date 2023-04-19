package co.talesbruno.mydiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.talesbruno.mydiary.presentation.main.MainScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@Composable
fun RootNavHost(
    noteViewModel: NoteViewModel,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val notes by noteViewModel.notes.collectAsStateWithLifecycle()
    val user by authViewModel.auth.collectAsStateWithLifecycle()
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authGraph(
            navController,
            authViewModel,
        )
        composable(route = Graph.MAIN) {
            notes.data?.let { notes ->
                user.data?.let { user ->
                    MainScreen(
                        noteViewModel = noteViewModel,
                        notes = notes,
                        user = user
                    )
                }
            }
        }
    }
}

object Graph {
    const val ROOT = "root"
    const val AUTH = "auth"
    const val MAIN = "main"
}