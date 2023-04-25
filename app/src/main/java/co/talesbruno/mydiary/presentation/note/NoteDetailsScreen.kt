package co.talesbruno.mydiary.presentation.note

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.presentation.viewmodel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun NoteDetailsScreen(
    note: Note,
    noteViewModel: NoteViewModel,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        note.title?.let { it1 -> Text(text = it1) }
        Spacer(modifier = Modifier.size(4.dp))
        note.note?.let { it1 -> Text(text = it1) }
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Update")
        }
        Button(onClick = { noteViewModel.delete(note) }) {
            Text(text = "Delete")
        }
    }
}
