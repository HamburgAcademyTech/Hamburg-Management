package az.hamburg.management.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import az.hamburg.management.domain.StudentRepository
import az.hamburg.management.domain.TeacherRepository
import kotlinx.coroutines.launch

@Composable
fun TeachersView(
    repository: TeacherRepository,
    repository2: StudentRepository,
    isType: String
) {
    val scope = rememberCoroutineScope()
    val teachers by repository.getTeachers().collectAsState(emptyList())
    val students by repository2.getStudents().collectAsState(emptyList())
    TeachersViewModel(
        teachers = teachers,
        addTeacher = { scope.launch { repository.addTeacher(it) } },
        updateTeacher = { scope.launch { repository.updateTeacher(it) } },
        deleteTeacher = { scope.launch { repository.deleteTeacher(it) } },
        deleteStudent = { scope.launch { repository2.deleteStudent(it) } },
        students = students,
        isType = isType
    )
}
