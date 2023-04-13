package co.talesbruno.mydiary.domain.usecase.noteusecase

import co.talesbruno.mydiary.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNote @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(userUuid: String, uuid: String, title: String, note: String) =
        noteRepository.update(userUuid, uuid, title, note)
}