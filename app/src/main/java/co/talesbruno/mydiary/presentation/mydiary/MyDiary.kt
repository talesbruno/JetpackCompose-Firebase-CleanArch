package co.talesbruno.mydiary.presentation.mydiary

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import co.talesbruno.mydiary.NavHost
import co.talesbruno.mydiary.presentation.bottomNavigationBar.BottomNavigationBar
import co.talesbruno.mydiary.presentation.bottomNavigationBar.bottomNavList
import com.google.firebase.auth.FirebaseUser

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDiary(
    navController: NavHostController,
    user: FirebaseUser
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Meu Diário") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController,
                bottomNavList
            )
        }
    ) {

    }
}