package az.hamburg.management.domain

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: String,
    val teacherId: String,
    val name: String,
    val number: String,
    val cost: String,
    val startDate: String,
    val level: String,
    val day: String,
    val hour: String,
    val month: Map<String, String>,
    val isDeleted: Boolean = false,
    val notes: String = ""
)
