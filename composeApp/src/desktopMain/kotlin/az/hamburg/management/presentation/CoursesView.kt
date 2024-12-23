package az.hamburg.management.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import az.hamburg.management.domain.Course
import az.hamburg.management.domain.CourseRepository
import kotlinx.coroutines.launch

@Composable
fun CoursesView(repository: CourseRepository) {
    val scope = rememberCoroutineScope()
    val courses by repository.getCourses().collectAsState(emptyList())
    CoursesViewModel(
        courses = courses
    )
}
