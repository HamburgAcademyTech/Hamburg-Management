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
    val months: Map<String, Map<String, String>>, // Dynamic year-month mapping
    val isDeleted: Boolean = false,
    val notes: String = "",
    val bg: String,
    val fg: String
)

class currentYear(
    val month: Map<String, String>,
    //val month25: Map<String, String>,
)
