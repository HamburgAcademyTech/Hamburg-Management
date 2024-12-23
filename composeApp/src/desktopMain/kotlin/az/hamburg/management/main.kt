package az.hamburg.management

import android.app.Application
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import az.hamburg.management.data.FirebaseCourseRepo
import az.hamburg.management.data.FirebaseStudentRepo
import az.hamburg.management.data.FirebaseTeacherRepo
import az.hamburg.management.presentation.CoursesView
import az.hamburg.management.presentation.TeachersView
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import java.awt.GraphicsEnvironment
import java.awt.Rectangle

fun main() = application {
    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
        val storage = mutableMapOf<String, String>()
        override fun clear(key: String) {
            storage.remove(key)
        }

        override fun log(msg: String) = println(msg)

        override fun retrieve(key: String) = storage[key]

        override fun store(key: String, value: String) = storage.set(key, value)
    })

    val options = FirebaseOptions(
        projectId = "hamburg-management",
        applicationId = "1:754009811758:web:3c4ad156ddd0ba28fc68bf",
        apiKey = "AIzaSyDPL59P4e_FrTft-0M4UGYXPkzSS3542_k"
    )

    Firebase.initialize(Application(), options)

    val courseRepo = remember { FirebaseCourseRepo() }
    val teacherRepo = remember { FirebaseTeacherRepo() }
    val studentRepo = remember { FirebaseStudentRepo() }
    //val screenSize: Rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration.bounds

    Window(
        onCloseRequest = ::exitApplication,
        title = "Hamburg Management",
        state = WindowState(size = DpSize(800.dp, 800.dp)), //WindowState(WindowPlacement.Fullscreen)
        resizable = true
        //alwaysOnTop = true,
        //undecorated = true
    ) {
        //App()
        //CoursesView(repository = courseRepo)
        TeachersView(repository = teacherRepo, repository2 = studentRepo)
    }
}
