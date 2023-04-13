package co.talesbruno.mydiary.domain.usecase.noteusecase

import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(userUuid: String, note: Note) =
        noteRepository.delete(userUuid, note)
}