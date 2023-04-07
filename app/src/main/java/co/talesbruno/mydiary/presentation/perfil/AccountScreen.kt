package co.talesbruno.mydiary.presentation.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PerfilScreen(
    user: FirebaseUser
){
    Column() {
        Text(text = "Seja bem-vindo ${user.displayName}")
    }
}