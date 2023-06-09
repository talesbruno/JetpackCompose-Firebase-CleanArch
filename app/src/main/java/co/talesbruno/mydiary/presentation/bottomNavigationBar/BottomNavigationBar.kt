package co.talesbruno.mydiary.presentation.bottomNavigationBar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    bottomNavList: List<BottomNavItem>
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        bottomNavList.forEach { bottomNavItem ->
            val selected = bottomNavItem.route == backStackEntry?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(bottomNavItem.route) },
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = bottomNavItem.name
                    )
                }
            )
        }
    }
}