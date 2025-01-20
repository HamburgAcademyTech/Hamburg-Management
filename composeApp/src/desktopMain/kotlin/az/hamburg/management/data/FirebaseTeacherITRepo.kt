package az.hamburg.management.data

import android.util.Log
import az.hamburg.management.domain.Class
import az.hamburg.management.domain.ClassRepository
import az.hamburg.management.domain.StudentC
import az.hamburg.management.domain.Teacher
import az.hamburg.management.domain.TeacherRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class FirebaseTeacherITRepo : TeacherRepository {
    private val firestore = Firebase.firestore

    override fun getTeachers() = flow {
        firestore.collection("Teachers_IT").snapshots.collect { querySnapshot ->
            val teachers = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<Teacher>()
            }
            emit(teachers)
        }
    }

    override fun getTeacherById(id: String) = flow {
        firestore.collection("Teachers_IT").document(id).snapshots.collect { documentSnapshot ->
            emit(documentSnapshot.data<Teacher>())
        }
    }

    override suspend fun addTeacher(teacher: Teacher) {
        val teacherId = generateRandomStringId()
        firestore.collection("Teachers_IT")
            .document(teacherId)
            .set(teacher.copy(id = teacherId))
    }

    override suspend fun updateTeacher(teacher: Teacher) {
        firestore.collection("Teachers_IT").document(teacher.id).set(teacher)
    }

    override suspend fun deleteTeacher(teacher: Teacher) {
        firestore.collection("Teachers_IT").document(teacher.id).delete()
    }

    private fun generateRandomStringId(length: Int = 20): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
