package co.talesbruno.mydiary.domain.repository

import co.talesbruno.mydiary.domain.Result
import co.talesbruno.mydiary.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNote(userUuid: String): Flow<Result<List<Note>>>
    suspend fun insert(userUuid: String, note: Note): co.talesbruno.mydiary.domain.Result<Boolean>
    suspend fun delete(userUuid: String, note: Note): co.talesbruno.mydiary.domain.Result<Boolean>
    suspend fun update(
        userUuid: String, uuid: String, title: String, note: String
    ): co.talesbruno.mydiary.domain.Result<Boolean>
}