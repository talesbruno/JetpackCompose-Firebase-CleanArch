package co.talesbruno.mydiary.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.presentation.navigation.NotesScreens
import co.talesbruno.mydiary.ui.theme.MyDiaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    navController: NavController
) {
    Card(
        onClick = { navController.navigateUp(NotesScreens.) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = {
            Row() {
                NoteIcon(
                    note.timestamp.toString(),
                    Modifier.weight(0.15f)
                )
                NoteDetails(
                    note.title,
                    Modifier.weight(0.85f)
                )
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MyDiaryTheme {
        NoteItem(note = Note("dadadadad", "ola", "dadadadad", 15))
    }
}