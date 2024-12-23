package az.hamburg.management.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import az.hamburg.management.domain.StudentRepository
import az.hamburg.management.domain.Teacher
import kotlinx.coroutines.launch

@Composable
fun StudentsView(
    repository: StudentRepository,
    teacherId: String,
    teacherName: String,
    teachers: List<Teacher>,
    isType: String
) {
    val scope = rememberCoroutineScope()
    val students by repository.getStudents().collectAsState(emptyList())
    StudentsViewModel(
        students = students,
        addStudent = { scope.launch { repository.addStudent(it) } },
        updateStudent = { scope.launch { repository.updateStudent(it) } },
        deleteStudent = { scope.launch { repository.deleteStudent(it) } },
        teacherId = teacherId,
        teacherName = teacherName,
        teachers = teachers,
        isType = isType
    )
}
