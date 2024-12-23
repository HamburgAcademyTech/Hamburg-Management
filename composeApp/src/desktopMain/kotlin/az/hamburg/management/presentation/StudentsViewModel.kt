package az.hamburg.management.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.hamburg.management.custom_view.TableCellLarge
import az.hamburg.management.custom_view.TableCellMedium
import az.hamburg.management.custom_view.TableCellSmall
import az.hamburg.management.custom_view.TableHeader
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher
import az.hamburg.management.popup.StudentCopyDialog
import az.hamburg.management.popup.StudentDeleteDialog
import az.hamburg.management.popup.StudentUpdateDialog
import az.hamburg.management.view.AddStudent
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun StudentsViewModel(
    students: List<Student>,
    addStudent: (Student) -> Unit,
    updateStudent: (Student) -> Unit,
    deleteStudent: (Student) -> Unit,
    teacherId: String,
    teacherName: String,
    teachers: List<Teacher>
){
    var studentDeleting = remember { mutableStateOf(false) }
    val selectedStudentForDelete = remember { mutableStateOf(Student("","","","","","","","","", mapOf(Pair("","")), false)) }
    var showPopup by remember { mutableStateOf(false) }
    var selectedStudentForUpdate by remember { mutableStateOf<Student?>(null) }
    var studentCopying = remember { mutableStateOf(false) }
    var selectedStudentForCopy by remember { mutableStateOf<Student?>(null) }
    var isAdmin = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Müəllim: $teacherName",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp)
        )

        TableHeader()

        Column (
            modifier = Modifier
                .border(
                    1.dp,
                    Color.LightGray,
                    RoundedCornerShape(12.dp)
                )
        ) {
            // Get the current date
            val currentDate = LocalDate.now()

            // Format the date (optional)
            val formatter = DateTimeFormatter.ofPattern("dd")
            val formattedDate = currentDate.format(formatter)
            println("Current date: $formattedDate")

            students.filter { it.teacherId == teacherId }.forEachIndexed{ index, it ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (it.isDeleted) Color.LightGray else Color.Transparent)
                    ) {
                        TableCellSmall("${index + 1}.", isHeader = true)
                        TableCellLarge(it.name)
                        TableCellLarge(it.number)
                        TableCellMedium(it.cost)
                        if (it.startDate.isNotEmpty()){
                            if (it.startDate.substring(0, 2) == formattedDate){
                                TableCellMedium(it.startDate, isTime = true)
                            }else{
                                TableCellMedium(it.startDate, isTime = false)
                            }
                        }else{
                            TableCellMedium(it.startDate, isTime = false)
                        }
                        TableCellMedium(it.level)
                        TableCellMedium(it.day)
                        TableCellMedium(it.hour)

                        val x = sortMonthData(it.month)
                        x.forEach { (month, value) ->
                            TableCellSmall(value)
                        }

                        IconButton(
                            onClick = {
                                selectedStudentForUpdate = it
                                showPopup = true
                        }){
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colors.primary
                            )
                        }

                        IconButton(
                            onClick = {
                                selectedStudentForDelete.value = it
                                studentDeleting.value = true
                            }){
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colors.error
                            )
                        }

                        IconButton(
                            onClick = {
                                selectedStudentForCopy = it
                                studentCopying.value = true
                            }){
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Copy",
                                tint = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .width(1900.dp)
                            .padding(horizontal = 5.dp)
                    )
                }
            }

            if (showPopup) {
                selectedStudentForUpdate?.let {
                    StudentUpdateDialog(student = it, onDismiss = { showPopup = false }) { updatedStudent ->
                        updateStudent(updatedStudent)
                        showPopup = false
                    }
                }
            }

            if (studentCopying.value) {
                selectedStudentForCopy?.let {
                    StudentCopyDialog(teachers = teachers, student = selectedStudentForCopy!!, onDismiss = { studentCopying.value=false }){ copiedStudent ->
                        addStudent(copiedStudent)
                        studentCopying.value = false
                    }
                }
            }

            if (studentDeleting.value){
                StudentDeleteDialog(name = selectedStudentForDelete.value.name, onDismiss = { studentDeleting.value = false }){
                    if (isAdmin.value){
                        deleteStudent(selectedStudentForDelete.value)
                        studentDeleting.value = false
                    }else{
                        val isDeleting = selectedStudentForDelete.value.copy(
                            isDeleted = true
                        )
                        updateStudent(isDeleting)
                        studentDeleting.value = false
                    }
                }
            }

            AddStudent(teacherId, addStudent)
        }
    }

    val monthOrder = listOf(
        "january", "february", "march", "april", "may", "june",
        "july", "august", "september", "october", "november", "december"
    )

    fun sortMonthData(data: Map<String, String>): List<Pair<String, String>> {
        return monthOrder.mapNotNull { month ->
            data[month]?.let { value -> month.capitalize() to value }
        }
    }
}
