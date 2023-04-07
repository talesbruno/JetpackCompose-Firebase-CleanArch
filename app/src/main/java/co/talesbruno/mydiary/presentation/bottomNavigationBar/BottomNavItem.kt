package co.talesbruno.mydiary.presentation.bottomNavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val name: String,
    val icon: ImageVector
)

val bottomNavList = listOf(
    BottomNavItem(
        "home",
        "Home",
        Icons.Default.Home
    ),
    BottomNavItem(
        "about",
        "About",
        Icons.Default.Info
    ),
    BottomNavItem(
        "account",
        "Account",
        Icons.Default.AccountCircle
    ),
)
