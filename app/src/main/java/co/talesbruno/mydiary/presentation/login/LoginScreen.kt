package co.talesbruno.mydiary.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.presentation.viewmodel.AuthViewModel
import co.talesbruno.mydiary.ui.theme.MyDiaryTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(
    onNavigateToCreateAccount: () -> Unit,
    authViewModel: AuthViewModel,
    onNavigateToMainScreen: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by authViewModel.signIn.collectAsStateWithLifecycle()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Seja Bem-vindo",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.size(16.dp))
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
            Button(onClick = { authViewModel.login(email, password) }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Não tem uma conta?")
            Text(
                text = "Criar conta",
                modifier = Modifier.clickable { onNavigateToCreateAccount() },
                color = MaterialTheme.colorScheme.secondary
            )
        }
        when (state) {
            is Result.Initial -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
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
            is Result.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyDiaryTheme {
    }
}