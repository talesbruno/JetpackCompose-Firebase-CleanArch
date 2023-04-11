package co.talesbruno.mydiary.domain.repository

import co.talesbruno.mydiary.domain.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun createAccountToFireStore(
        name: String,
        email: String,
        password: String
    ): Flow<co.talesbruno.mydiary.domain.Result<FirebaseUser>>

    suspend fun login(
        email: String,
        password: String
    ): Flow<co.talesbruno.mydiary.domain.Result<FirebaseUser>>

    fun logout()

    suspend fun isUserOnline(): Flow<co.talesbruno.mydiary.domain.Result<User>>
}