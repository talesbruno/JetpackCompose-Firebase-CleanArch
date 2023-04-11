package co.talesbruno.mydiary.domain.model

data class User(
    val uuid: String? = null,
    val name: String? = null,
    val email: String? = null,
    val timestamp: Long? = 0
)
