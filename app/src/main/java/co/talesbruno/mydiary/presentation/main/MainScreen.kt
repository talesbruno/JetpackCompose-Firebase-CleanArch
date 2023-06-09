package co.talesbruno.mydiary.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.presentation.bottomNavigationBar.BottomNavigationBar
import co.talesbruno.mydiary.presentation.bottomNavigationBar.bottomNavList
import co.talesbruno.mydiary.presentation.navigation.MainGraph
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel
import co.talesbruno.mydiary.ui.theme.MyDiaryTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    noteViewModel: NoteViewModel,
    authViewModel: AuthViewModel,
    notes: List<Note>,
    user: User,
    onNavigateToLoginScreen: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController,
                bottomNavList
            )
        },
        content = {
            MainGraph(
                navController = navController,
                noteViewModel = noteViewModel,
                notes = notes,
                user = user,
                authViewModel = authViewModel,
                onNavigateToLoginScreen = onNavigateToLoginScreen
            )
        }
    )
}
