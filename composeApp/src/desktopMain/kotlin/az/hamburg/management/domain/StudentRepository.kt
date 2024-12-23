package az.hamburg.management.domain

import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getStudents(): Flow<List<Student>>
    fun getStudentById(id: String): Flow<Student?>
    suspend fun addStudent(student: Student)
    suspend fun updateStudent(student: Student)
    suspend fun deleteStudent(student: Student)
}
