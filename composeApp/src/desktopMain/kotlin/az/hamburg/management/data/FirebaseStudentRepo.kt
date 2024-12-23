package az.hamburg.management.data

import az.hamburg.management.domain.Student
import az.hamburg.management.domain.StudentRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow

class FirebaseStudentRepo : StudentRepository {
    private val firestore = Firebase.firestore

    override fun getStudents() = flow {
        firestore.collection("Students").snapshots.collect { querySnapshot ->
            val students = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<Student>()
            }
            emit(students)
        }
    }

    override fun getStudentById(id: String) = flow {
        firestore.collection("Students").document(id).snapshots.collect { documentSnapshot ->
            emit(documentSnapshot.data<Student>())
        }
    }

    override suspend fun addStudent(student: Student) {
        val studentId = generateRandomStringId()
        firestore.collection("Students")
            .document(studentId)
            .set(student.copy(id = studentId))
    }

    override suspend fun updateStudent(student: Student) {
        firestore.collection("Students").document(student.id).set(student)
    }

    override suspend fun deleteStudent(student: Student) {
        firestore.collection("Students").document(student.id).delete()
    }

    private fun generateRandomStringId(length: Int = 20): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
