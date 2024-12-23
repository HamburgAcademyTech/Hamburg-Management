package az.hamburg.management.domain

import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    fun getTeachers(): Flow<List<Teacher>>
    fun getTeacherById(id: String): Flow<Teacher?>
    suspend fun addTeacher(teacher: Teacher)
    suspend fun updateTeacher(teacher: Teacher)
    suspend fun deleteTeacher(teacher: Teacher)
}
