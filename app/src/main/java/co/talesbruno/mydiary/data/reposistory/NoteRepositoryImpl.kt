package co.talesbruno.mydiary.data.reposistory

import co.talesbruno.mydiary.domain.util.Result
import co.talesbruno.mydiary.domain.model.Note
import co.talesbruno.mydiary.domain.repository.NoteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val firebaseFirestore: FirebaseFirestore
) : NoteRepository {
    override fun getAllNote(userUuid: String): Flow<Result<List<Note>>> = callbackFlow {
        val notesCollection = firebaseFirestore
            .collection("notes")
            .document(userUuid)
            .collection("userNotes")
        val listenerRegistration = notesCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Result.Error(error.message ?: "Unknown error", null))
            } else {
                val notes = snapshot?.documents?.mapNotNull { document ->
                    document.toObject(Note::class.java)?.copy(uuid = document.id)
                }
                trySend(Result.Success("Lista de notas",notes ?: emptyList()))
            }
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }

    override suspend fun insert(userUuid: String, note: Note): Result<Boolean> {
        val userNotesRef = firebaseFirestore
            .collection("notes")
            .document(userUuid)
            .collection("userNotes")
        return try {
            userNotesRef.add(note).await()
            Result.Success("Inserido com sucesso", true)
        } catch (e: Exception) {
            Result.Error(e.message.toString(), false)
        }
    }

    override suspend fun delete(
        userUuid: String,
        note: Note
    ): Result<Boolean> {
        val userNoteRef = firebaseFirestore
            .collection("notes")
            .document(userUuid)
            .collection("userNotes")
            .document(note.uuid)
        return try {
            userNoteRef.delete().await()
            Result.Success("Deletado com sucesso!", true)
        } catch (e: Exception) {
            Result.Error(e.message.toString(), false)
        }
    }

    override suspend fun update(
        userUuid: String, uuid: String, title: String, note: String
    ): Result<Boolean> {
        val userNoteRef = firebaseFirestore
            .collection("notes")
            .document(userUuid)
            .collection("userNotes")
            .document(uuid)
        return try {
            userNoteRef.update(
                mapOf(
                    "title" to title,
                    "note" to note
                )
            ).await()
            Result.Success("Atualizado com sucesso", true)
        }catch (e: Exception){
            Result.Error(e.message.toString(), false)
        }

    }
}