package co.talesbruno.mydiary.domain.util

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
){
    class Initial<T>(data: T? = null) : Result<T>(data)
    class Success<T>(message: String, data: T? = null) : Result<T>(data, message)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)
}