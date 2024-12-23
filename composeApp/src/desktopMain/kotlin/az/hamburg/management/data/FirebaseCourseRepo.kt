package az.hamburg.management.data

import az.hamburg.management.domain.Course
import az.hamburg.management.domain.CourseRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow

class FirebaseCourseRepo : CourseRepository {
    private val firestore = Firebase.firestore

    override fun getCourses() = flow {
        firestore.collection("Courses").snapshots.collect { querySnapshot ->
            val courses = querySnapshot.documents.map { documentSnapshot ->
                // documentSnapshot.data<Course>()
                Course(
                    id = documentSnapshot.id,
                    name = documentSnapshot.get("name") ?: "",
                    image = documentSnapshot.get("image") ?: ""
                )
            }
            emit(courses)
        }
    }
}
