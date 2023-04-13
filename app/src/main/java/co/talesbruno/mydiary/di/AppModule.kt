package co.talesbruno.mydiary.di

import co.talesbruno.mydiary.data.reposistory.AuthRepositoryImpl
import co.talesbruno.mydiary.data.reposistory.NoteRepositoryImpl
import co.talesbruno.mydiary.domain.repository.AuthRepository
import co.talesbruno.mydiary.domain.repository.NoteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore) : AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesNoteRepositoryImpl(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore) : NoteRepository {
        return NoteRepositoryImpl(firebaseAuth, firebaseFirestore)
    }
}