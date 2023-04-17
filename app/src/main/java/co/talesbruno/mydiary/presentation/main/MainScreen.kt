package co.talesbruno.mydiary.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.presentation.bottomNavigationBar.BottomNavigationBar
import co.talesbruno.mydiary.presentation.bottomNavigationBar.bottomNavList
import co.talesbruno.mydiary.presentation.navigation.MainGraph
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    noteViewModel: NoteViewModel,
    notes: List<Note>,
    user: User,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "Meu Diário") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    MaterialTheme.colorScheme.primary
//                )
//            )
//        },
        bottomBar = {
            BottomNavigationBar(
                navController,
                bottomNavList
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        MainGraph(
            navController = navController,
            authViewModel = authViewModel,
            noteViewModel = noteViewModel,
            notes = notes,
            user = user
        )
    }
}