package co.talesbruno.mydiary

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import co.talesbruno.mydiary.presentation.about.AboutScreen
import co.talesbruno.mydiary.presentation.home.HomeScreen
import co.talesbruno.mydiary.presentation.login.CreateAccount
import co.talesbruno.mydiary.presentation.login.LoginScreen
import co.talesbruno.mydiary.presentation.mydiary.MyDiary
import co.talesbruno.mydiary.presentation.perfil.PerfilScreen
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel
) {
    val state by authViewModel.auth.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "login") {
        navigation(startDestination = "signin", route = "login") {
            composable("signin") {
                LoginScreen(
                    onNavigateToCreateAccount = { navController.navigate("signup") },
                    authViewModel = authViewModel,
                    onNavigateToHomeScreen = { navController.navigate("homescreen") }
                )
            }
            composable("signup") {
                CreateAccount(
                    authViewModel = authViewModel,
                    onNavigateToHomeScreen = { navController.navigate("homescreen") }
                )
            }
        }
        navigation(startDestination = "home", route = "mydiary") {
            composable("home") {
                HomeScreen()
            }
            composable("about") {
                AboutScreen()
            }
            composable("perfil") {
                state.data?.let { user -> PerfilScreen(user) }
            }
        }
    }
}