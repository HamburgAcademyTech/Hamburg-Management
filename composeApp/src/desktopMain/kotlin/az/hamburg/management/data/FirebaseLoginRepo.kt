package az.hamburg.management.data

import android.util.Log
import az.hamburg.management.domain.Class
import az.hamburg.management.domain.ClassRepository
import az.hamburg.management.domain.Login
import az.hamburg.management.domain.LoginRepository
import az.hamburg.management.domain.StudentC
import az.hamburg.management.domain.Teacher
import az.hamburg.management.domain.TeacherRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class FirebaseLoginRepo : LoginRepository {
    private val firestore = Firebase.firestore

    override fun getLogins() = flow {
        firestore.collection("Logins").snapshots.collect { querySnapshot ->
            val logins = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<Login>()
            }
            emit(logins)
        }
    }
}
