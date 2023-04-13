package co.talesbruno.mydiary.domain.model

data class Note(
    val uuid: String,
    val title: String,
    val note: String,
    val timestamp: Long
)
