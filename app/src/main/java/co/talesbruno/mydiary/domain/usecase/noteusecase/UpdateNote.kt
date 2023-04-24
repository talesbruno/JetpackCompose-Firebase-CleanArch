package co.talesbruno.mydiary.domain.usecase.noteusecase

import co.talesbruno.mydiary.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNote @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(uuid: String, title: String?, note: String?) =
        noteRepository.update(uuid, title, note)
}