package co.talesbruno.mydiary.domain.repository

import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.domain.util.Result
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun createAccountToFireStore(
        name: String,
        email: String,
        password: String
    ): Flow<Result<FirebaseUser>>

    suspend fun login(
        email: String,
        password: String
    ): Flow<Result<FirebaseUser>>

    fun logout()

    suspend fun isUserOnline(): Flow<Result<User>>
}