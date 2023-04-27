package co.talesbruno.mydiary.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun CreateAccount(
    authViewModel: AuthViewModel,
    onNavigateToMainScreen: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by authViewModel.signUp.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crie sua Conta preechendo os campos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Nome") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "E-mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {
                    authViewModel.createAccountToFireStore(name, email, password)
                }
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = "Cadastrar")
            }
        }
        when (state) {
            is Result.Initial -> {

            }
            is Result.Success -> {
                onNavigateToMainScreen()
            }
            is Result.Error -> {
                scope.launch {
                    state.message?.let {
                        val result = snackbarHostState.showSnackbar(
                            it, "Ok",
                        )
                    }
                }
            }
            is Result.Loading -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }
    }
}