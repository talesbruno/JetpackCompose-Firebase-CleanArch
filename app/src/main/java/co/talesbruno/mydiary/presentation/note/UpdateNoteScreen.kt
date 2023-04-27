package co.talesbruno.mydiary.presentation.note

import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun UpdateNoteScreen(
    note: Note,
    noteViewModel: NoteViewModel,
    navController: NavController,
) {
    val createNote by noteViewModel.update.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf(note.title) }
    var nota by remember { mutableStateOf(note.note) }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Atualizando nota")
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        })
    }) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            title?.let { it1 ->
                OutlinedTextField(
                    value = it1,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
            nota?.let { it1 ->
                OutlinedTextField(
                    value = it1,
                    onValueChange = { nota = it },
                    label = { Text(text = "Note") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {
                note.uuid?.let { uuid ->
                    noteViewModel.update(
                        uuid,
                        title,
                        nota
                    )
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Salvar",
                    fontSize = 16.sp
                )
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