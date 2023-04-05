package co.talesbruno.mydiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.talesbruno.mydiary.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signUp =
        MutableStateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>>(co.talesbruno.mydiary.domain.Result.Initial())
    val signUp: StateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>> = _signUp

    private val _signIn =
        MutableStateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>>(co.talesbruno.mydiary.domain.Result.Initial())
    val signIn: StateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>> = _signIn

    private val _auth =
        MutableStateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>>(co.talesbruno.mydiary.domain.Result.Initial())
    val auth: StateFlow<co.talesbruno.mydiary.domain.Result<FirebaseUser>> = _auth

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                _auth.value = it
            }
        }
    }

    fun isUserOnline() {
        viewModelScope.launch {
            authRepository.isUserOnline().collect {
                _auth.value = it
            }
        }
    }

    fun logout() {
        authRepository.logout()
    }

    fun createAccountToFireStore(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.createAccountToFireStore(name, email, password).collect {
                _auth.value = it
            }
        }
    }
}