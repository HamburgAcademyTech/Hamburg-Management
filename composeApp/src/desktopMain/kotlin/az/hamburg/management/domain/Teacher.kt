package az.hamburg.management.domain

import kotlinx.serialization.Serializable

@Serializable
data class Teacher(
    val id: String,
    var name: String
)
