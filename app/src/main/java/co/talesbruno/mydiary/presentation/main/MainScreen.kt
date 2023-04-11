package co.talesbruno.mydiary.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.talesbruno.mydiary.presentation.bottomNavigationBar.BottomNavigationBar
import co.talesbruno.mydiary.presentation.bottomNavigationBar.bottomNavList
import co.talesbruno.mydiary.presentation.navigation.MainGraph
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
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
        }
    ) {
        MainGraph(
            navController = navController,
            authViewModel = authViewModel,
        )
    }
}