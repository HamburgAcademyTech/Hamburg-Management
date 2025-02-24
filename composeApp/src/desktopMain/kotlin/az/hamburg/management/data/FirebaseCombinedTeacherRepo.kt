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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
