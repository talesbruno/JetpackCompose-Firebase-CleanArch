package co.talesbruno.mydiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.talesbruno.mydiary.domain.model.User
import co.talesbruno.mydiary.domain.repository.AuthRepository
import co.talesbruno.mydiary.domain.util.Result
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signUp =
        MutableStateFlow<Result<FirebaseUser>>(Result.Initial())
    val signUp: StateFlow<Result<FirebaseUser>> = _signUp

    private val _signIn =
        MutableStateFlow<Result<FirebaseUser>>(Result.Initial())
    val signIn: StateFlow<Result<FirebaseUser>> = _signIn

    private val _auth =
        MutableStateFlow<Result<User>>(Result.Initial())
    val auth: StateFlow<Result<User>> = _auth

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                _signIn.value = it
            }
        }
    }

    init {
        isUserOnline()
    }

    private fun isUserOnline() {
        viewModelScope.launch {
            authRepository.isUserOnline().collect {
                _auth.value = it
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _signIn.value = Result.Initial()
        _signUp.value = Result.Initial()
    }

    fun createAccountToFireStore(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.createAccountToFireStore(name, email, password).collect {
                _signUp.value = it
            }
        }
    }
}