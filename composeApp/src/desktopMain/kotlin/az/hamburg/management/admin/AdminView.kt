package az.hamburg.management.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.ScrollingView
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.StudentRepository
import az.hamburg.management.domain.Teacher
import az.hamburg.management.domain.TeacherRepository
import az.hamburg.management.presentation.StudentsViewModel
import az.hamburg.management.presentation.TeachersViewModel
import az.hamburg.management.view.BarChart
import az.hamburg.management.view.BarChartWithLabels
import az.hamburg.management.view.PieChart
import az.hamburg.management.view.PieChartWithLabels
import az.hamburg.management.view.VerticalBarChart
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AdminView(
    repository: TeacherRepository,
    repository2: StudentRepository,
    repositoryIt: TeacherRepository,
    repository2It: StudentRepository,
    isType: String
) {
    val scope = rememberCoroutineScope()
    val teachers by repository.getTeachers().collectAsState(emptyList())
    val students by repository2.getStudents().collectAsState(emptyList())
    val teachersIt by repositoryIt.getTeachers().collectAsState(emptyList())
    val studentsIt by repository2It.getStudents().collectAsState(emptyList())
    var repoType by remember { mutableStateOf(1) }
    var chartType by remember { mutableStateOf(1) }
    var chartYear by remember { mutableStateOf("2024") }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (repoType==1) Color.Black else Color.LightGray,
                    contentColor = if (repoType==1) Color.LightGray else Color.Black
                ),
                onClick = {
                    repoType = 1
                }){
                Text(
                    text = "German"
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (repoType==2) Color.Black else Color.LightGray,
                    contentColor = if (repoType==2) Color.LightGray else Color.Black
                ),
                onClick = {
                    repoType = 2
                }){
                Text(
                    text = "IT"
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (repoType==3) Color.Black else Color.LightGray,
                    contentColor = if (repoType==3) Color.LightGray else Color.Black
                ),
                onClick = {
                    repoType = 3
                }){
                Text(
                    text = "Statistics"
                )
            }
        }

        if (repoType==1){
            TeachersViewModel(
                teachers = teachers,
                addTeacher = { scope.launch { repository.addTeacher(it) } },
                updateTeacher = { scope.launch { repository.updateTeacher(it) } },
                deleteTeacher = { scope.launch { repository.deleteTeacher(it) } },
                deleteStudent = { scope.launch { repository2.deleteStudent(it) } },
                students = students,
                isType = "$isType.german"
            )
        }else if (repoType==2){
            TeachersViewModel(
                teachers = teachersIt,
                addTeacher = { scope.launch { repositoryIt.addTeacher(it) } },
                updateTeacher = { scope.launch { repositoryIt.updateTeacher(it) } },
                deleteTeacher = { scope.launch { repositoryIt.deleteTeacher(it) } },
                deleteStudent = { scope.launch { repository2It.deleteStudent(it) } },
                students = studentsIt,
                isType = "$isType.it"
            )
        }else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartType == 1) Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartType == 1) Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartType = 1
                    }
                ) {
                    Text(
                        text = "German"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartType == 2) Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartType == 2) Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartType = 2
                    }
                ) {
                    Text(
                        text = "It"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartYear == "2023") Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartYear == "2023") Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartYear = "2023"
                    }
                ) {
                    Text(
                        text = "2023"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartYear == "2024") Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartYear == "2024") Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartYear = "2024"
                    }
                ) {
                    Text(
                        text = "2024"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartYear == "2025") Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartYear == "2025") Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartYear = "2025"
                    }
                ) {
                    Text(
                        text = "2025"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (chartYear == "2026") Color(0xFF059669) else Color(0xFF6EE7B7),
                        contentColor = if (chartYear == "2026") Color.White else Color(0xFF065F46)
                    ),
                    onClick = {
                        chartYear = "2026"
                    }
                ) {
                    Text(
                        text = "2026"
                    )
                }
            }

            val studentData = mapOf(
                "2021" to 10f,
                "2022" to 15f,
                "2023" to 30f,
                "2024" to 20f
            )
            // Count students by start year
            val startYears: Map<String, Float> = students
                .groupingBy { "20" + it.startDate.split("/")[2] }
                .eachCount()
                .mapValues { it.value.toFloat() } // Convert Int to Float
            val startYearsIt: Map<String, Float> = studentsIt
                .groupingBy { "20" + it.startDate.split("/")[2] }
                .eachCount()
                .mapValues { it.value.toFloat() } // Convert Int to Float

            // Count payment days across months
            val paymentDays: Map<String, Float> = students
                .flatMap { it.months.values.flatMap { month -> month.values } } // Get all payment days
                .filter { it.isNotEmpty() } // Remove empty values
                .groupingBy { it }
                .eachCount() // Count occurrences
                .mapValues { it.value.toFloat() } // Convert Int to Float
            val paymentDays2024: Map<String, Float> = students
                .flatMap { it.months["2025"]?.values ?: emptyList() } // Get only 2024 payments
                .filter { it.isNotEmpty() } // Remove empty values
                .groupingBy { it }
                .eachCount()
                .mapValues { it.value.toFloat() } // Convert Int to Float
            val paymentDaysByMonth2024: Map<String, Float> = students
                .flatMap { student ->
                    student.months[chartYear]?.map { (month, day) -> month.capitalize().substring(0,3) to day } ?: emptyList() //"2024 ${month.capitalize()}"
                }
                .filter { it.second.isNotEmpty() } // Remove empty payment days
                .groupingBy { it.first } // Group by "2024 Month"
                .eachCount()
                .mapValues { it.value.toFloat() } // Convert to Float
            val paymentDaysByMonth2024It: Map<String, Float> = studentsIt
                .flatMap { student ->
                    student.months[chartYear]?.map { (month, day) -> month.capitalize().substring(0,3) to day } ?: emptyList() //"2024 ${month.capitalize()}"
                }
                .filter { it.second.isNotEmpty() } // Remove empty payment days
                .groupingBy { it.first } // Group by "2024 Month"
                .eachCount()
                .mapValues { it.value.toFloat() } // Convert to Float

            Row{
                PieChartWithLabels(data = if (chartType==1) paymentDaysByMonth2024 else  paymentDaysByMonth2024It)
                Spacer(modifier = Modifier.width(20.dp))
                //PieChartWithLabels(data = startYears)
                VerticalBarChart(data = if (chartType==1) startYears else startYearsIt)
            }
        }
    }
}
