package co.talesbruno.mydiary.presentation.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PerfilScreen(
    authViewModel: AuthViewModel
){
    val user by authViewModel.auth.collectAsStateWithLifecycle()
    Column() {
        Text(text = "Seja bem-vindo ${user.data?.displayName}")
    }
}