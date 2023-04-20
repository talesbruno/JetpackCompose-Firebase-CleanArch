package co.talesbruno.mydiary.presentation.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            .fillMaxWidth()
            .background(color = Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Seja bem-vindo ${user.name}")
        Button(
            onClick = {
                authViewModel.logout()
                onNavigateToLoginScreen()
            }
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}