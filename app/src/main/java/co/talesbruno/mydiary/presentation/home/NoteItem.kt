package co.talesbruno.mydiary.presentation.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.talesbruno.mydiary.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    onNavigateToDetailScreen: (Note) -> Unit
) {
    Card(
        onClick = { onNavigateToDetailScreen(note) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = {
            Row() {
                NoteIcon(
                    note.timestamp,
                    Modifier.weight(0.15f)
                )
                note.title?.let {
                    NoteDetails(
                        it,
                        Modifier.weight(0.85f)
                    )
                }
            }
        },
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun NoteIcon(date: String, modifier: Modifier) {
    Text(
        text = date,
        modifier.padding(10.dp)

    )
}

@Composable
fun NoteDetails(title: String, modifier: Modifier) {
    Text(
        text = title,
        modifier.padding(10.dp)
    )
}


