package co.talesbruno.mydiary.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.talesbruno.mydiary.domain.model.Note

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    notes: List<Note>,
    onNavigateToDetailScreen: (Note) -> Unit,
    onNavigateToCreateNoteScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(text = "Minhas anotações")
        Spacer(modifier = Modifier.size(4.dp))
        LazyColumn() {
            items(items = notes) { note ->
                NoteItem(
                    note = note,
                    onNavigateToDetailScreen = onNavigateToDetailScreen
                )
            }
        }
        FloatingActionButton(
            onClick = { onNavigateToCreateNoteScreen() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

