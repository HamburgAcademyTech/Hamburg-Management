package az.hamburg.management.domain

import kotlinx.serialization.Serializable

@Serializable
data class Class(
    val course_id: String,
    val teacher: String,
    val studentList: List<StudentC> = emptyList()
)

@Serializable
data class StudentC(
    val cost: String,
    val name: String,
    val start_date: String,
    val month: Map<String, String>
)
