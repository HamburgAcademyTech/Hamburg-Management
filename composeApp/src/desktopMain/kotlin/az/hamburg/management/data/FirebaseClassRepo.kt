package az.hamburg.management.data

import android.util.Log
import az.hamburg.management.domain.Class
import az.hamburg.management.domain.ClassRepository
import az.hamburg.management.domain.StudentC
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class FirebaseClassRepo : ClassRepository {
    private val firestore = Firebase.firestore

    override fun getClasses() = flow {
        firestore.collection("Classes").snapshots.collect { querySnapshot ->
            val classes = querySnapshot.documents.map { classDoc ->
                val studentsSnapshots = firestore.collection("Classes")
                    .document(classDoc.id)
                    .collection("Students")
                    .get()

                val students = studentsSnapshots.documents.map { studentDoc ->
                    StudentC(
                        cost = studentDoc.get("cost") ?: "",
                        name = studentDoc.get("name") ?: "",
                        start_date = studentDoc.get("start_date"),
                        month = studentDoc.get("month")
                    )
                }

                // Return a Class object with the full list of students
                Class(
                    course_id = classDoc.get("course_id") ?: "",
                    teacher = classDoc.get("teacher") ?: "",
                    studentList = students // Add a new property 'studentList' for the full list
                )
            }
            emit(classes)
        }
    }
}
