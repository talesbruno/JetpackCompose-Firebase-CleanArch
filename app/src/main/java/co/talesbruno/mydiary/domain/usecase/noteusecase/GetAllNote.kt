package co.talesbruno.mydiary.domain.usecase.noteusecase

import co.talesbruno.mydiary.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNote @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(userUuid: String) = noteRepository.getAllNote(userUuid)
}