package co.talesbruno.mydiary.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.talesbruno.mydiary.domain.model.Note

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    notes: List<Note>,
    onNavigateToCreateNoteScreen: () -> Unit,
    onNavigateToDetailScreen: (Note) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Minhas Notas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(22.dp))
            LazyColumn() {
                items(items = notes) { note ->
                    NoteItem(
                        note = note,
                        onNavigateToDetailScreen = onNavigateToDetailScreen,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
        FloatingActionButton(
            onClick = { onNavigateToCreateNoteScreen() },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }

}



