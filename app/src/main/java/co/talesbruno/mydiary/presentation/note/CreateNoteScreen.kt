package co.talesbruno.mydiary.presentation.note

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun CreateNoteScreen(
    noteViewModel: NoteViewModel,
    navController: NavController
) {
    val createNote by noteViewModel.insert.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Note")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cadastrando uma nova Nota",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text(text = "Note") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { noteViewModel.insert(Note(title = title, note = note)) }) {
                Text(text = "Cadastrar")
            }
        }
        when (createNote) {
            is Result.Initial -> {

            }
            is Result.Success -> {
                scope.launch {
                    createNote.message?.let {
                        val result = snackbarHostState.showSnackbar(
                            it, "Ok",
                        )
                    }
                }
            }
            is Result.Error -> {
                scope.launch {
                    createNote.message?.let {
                        val result = snackbarHostState.showSnackbar(
                            it, "Ok",
                        )
                    }
                }
            }
            is Result.Loading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}