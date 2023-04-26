package co.talesbruno.mydiary.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.presentation.navigation.MainScreens

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

        ) {
            LazyColumn(
            ) {
                items(items = notes) { note ->
                    NoteItem(
                        note = note,
                        onNavigateToDetailScreen = onNavigateToDetailScreen,
                    )
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



