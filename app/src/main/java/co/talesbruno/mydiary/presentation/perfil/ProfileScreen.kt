package co.talesbruno.mydiary.presentation.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    user: User,
    onNavigateToLoginScreen: () -> Unit,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Seja bem-vindo ${user.name}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                authViewModel.logout()
                onNavigateToLoginScreen()
            }
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
            Text(text = "Sair")
        }
    }
}