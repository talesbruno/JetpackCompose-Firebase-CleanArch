package co.talesbruno.mydiary.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.talesbruno.mydiary.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    onNavigateToDetailScreen: (Note) -> Unit,
) {
    Card(
        onClick = { onNavigateToDetailScreen(note) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    note.title?.let {
                        NoteTitle(
                            it,
                        )
                    }
                    Divider()
                    note.note?.let {
                        NoteDetails(
                            it,
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NoteTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun NoteDetails(note: String) {
    Text(
        text = note,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(16.dp)
    )
}


