package co.talesbruno.mydiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.usecase.noteusecase.DeleteNote
import co.talesbruno.mydiary.domain.usecase.noteusecase.GetAllNote
import co.talesbruno.mydiary.domain.usecase.noteusecase.InsertNote
import co.talesbruno.mydiary.domain.usecase.noteusecase.UpdateNote
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val user: FirebaseAuth,
    private val deleteNote: DeleteNote,
    private val insertNote: InsertNote,
    private val updateNote: UpdateNote,
    private val getAllNote: GetAllNote
) : ViewModel() {
    private val _notes = MutableStateFlow<co.talesbruno.mydiary.domain.util.Result<List<Note>>>(
        co.talesbruno.mydiary.domain.util.Result.Loading()
    )
    val notes: StateFlow<co.talesbruno.mydiary.domain.util.Result<List<Note>>> = _notes

    private val _delete = MutableStateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>>(
        co.talesbruno.mydiary.domain.util.Result.Loading()
    )
    val delete: StateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>> = _delete

    private val _insert = MutableStateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>>(
        co.talesbruno.mydiary.domain.util.Result.Loading()
    )
    val insert: StateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>> = _insert

    private val _update = MutableStateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>>(
        co.talesbruno.mydiary.domain.util.Result.Loading()
    )
    val update: StateFlow<co.talesbruno.mydiary.domain.util.Result<Boolean>> = _update

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNote().collect {
                _notes.value = it
            }
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            _delete.value = deleteNote(note)
        }
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            _insert.value = insertNote(note)
        }
    }

    fun update(title: String, note: String) {
        viewModelScope.launch {
            _update.value = updateNote(title, note)
        }
    }


}