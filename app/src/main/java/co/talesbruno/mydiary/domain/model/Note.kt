package co.talesbruno.mydiary.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    val uuid: String? = null,
    val title: String? = "",
    val note: String? = "",
    val timestamp: String = getDateCreated()
)
fun getDateCreated(): String{
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

