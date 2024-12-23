package az.hamburg.management.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import az.hamburg.management.domain.ClassRepository
import az.hamburg.management.domain.CourseRepository

@Composable
fun ClassesView(repository: ClassRepository, currentCourse:String) {
    val scope = rememberCoroutineScope()
    val classes by repository.getClasses().collectAsState(emptyList())
    ClassesViewModel(
        classes = classes,
        currentCourse = currentCourse
    )
}
