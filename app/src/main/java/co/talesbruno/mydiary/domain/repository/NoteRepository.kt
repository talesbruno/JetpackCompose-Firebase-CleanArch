package co.talesbruno.mydiary.domain.repository

import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNote(userUuid: String): Flow<Result<List<Note>>>
    suspend fun insert(userUuid: String, note: Note): Result<Boolean>
    suspend fun delete(userUuid: String, note: Note): Result<Boolean>
    suspend fun update(
        userUuid: String, uuid: String, title: String, note: String
    ): Result<Boolean>
}