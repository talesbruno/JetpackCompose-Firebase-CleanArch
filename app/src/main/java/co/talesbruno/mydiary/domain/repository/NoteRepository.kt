package co.talesbruno.mydiary.domain.repository

import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNote(): Flow<Result<List<Note>>>
    suspend fun insert(note: Note): Result<Boolean>
    suspend fun delete(note: Note): Result<Boolean>
    suspend fun update(note: Note): Result<Boolean>
}