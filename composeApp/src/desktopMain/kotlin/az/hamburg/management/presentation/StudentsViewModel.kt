package az.hamburg.management.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.hamburg.management.custom_view.TableCellLarge
import az.hamburg.management.custom_view.TableCellMedium
import az.hamburg.management.custom_view.TableCellName
import az.hamburg.management.custom_view.TableCellSmall
import az.hamburg.management.custom_view.TableHeader
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher
import az.hamburg.management.popup.StudentCopyDialog
import az.hamburg.management.popup.StudentDeleteDialog
import az.hamburg.management.popup.StudentNoteDialog
import az.hamburg.management.popup.StudentSettingDialog
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
    teachers: List<Teacher>,
    isType: String
){
    var studentDeleting = remember { mutableStateOf(false) }
    val selectedStudentForDelete = remember { mutableStateOf(Student("","","","","","","","","", mapOf(Pair("","")), false, notes = "", bg = "", fg = "")) }
    var studentUpdating by remember { mutableStateOf(false) }
    var selectedStudentForUpdate by remember { mutableStateOf<Student?>(null) }
    var studentCopying = remember { mutableStateOf(false) }
    var selectedStudentForCopy by remember { mutableStateOf<Student?>(null) }
    var studentNoting = remember { mutableStateOf(false) }
    var selectedStudentForNote by remember { mutableStateOf<Student?>(null) }
    var studentSetting = remember { mutableStateOf(false) }
    var selectedStudentForSet by remember { mutableStateOf<Student?>(null) }

    var clickCount by remember { mutableStateOf(0) }
    var isEditable by remember { mutableStateOf(false) }
    var daySwitch by remember { mutableStateOf("") }
    var myMonthOrder by remember { mutableStateOf(mutableListOf<String>()) }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            Text(
                text = "Müəllim: $teacherName",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = daySwitch,
                onValueChange = { daySwitch = it },
                label = { Text("Gün") },
                modifier = Modifier
                    .size(120.dp, 70.dp)
                    .align(Alignment.CenterVertically),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    students.filter { it.teacherId == teacherId }.forEach { student ->
                        val trimDay = student.startDate.substring(3, 5)
                        val switchedDay = (trimDay.toInt() + daySwitch.toInt()).toString()
                        println("TrimDay: $trimDay")
                        println("SwitchedDay: $switchedDay")
                        
                        val updatedStudent = student.copy(
                            startDate = "${student.startDate.substring(0, 3)}$switchedDay${student.startDate.substring(5)}"
                        )
                        updateStudent(updatedStudent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF006400),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Ok")
            }
        }

        students.filter { it.teacherId == teacherId }.forEach { it ->
            val y = sortMonthData(it.month25)
            y.forEach{ (month, value) ->
                if (value.isNotEmpty()){
                    myMonthOrder.add(month)
                }
            }
        }

        TableHeader(
            myMonthOrder.distinct().sortedBy { getMonthIndex(it) }
        )

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
                        TableCellName(it.name, bg = if(it.bg != "") stringToComposeColor(it.bg) else Color.Transparent, fg = it.fg)
                        TableCellLarge(it.number, modifier = Modifier)
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

                        val emptyMonth = filterMonths(it)
                        println("Student: ${it.name}")
                        emptyMonth.forEach { (month, value) ->
                            println("$month: $value")
                        }

                        val x = sortMonthData(it.month25)
                        x.forEach { (month, value) ->
                            if (myMonthOrder.distinct().sortedBy { getMonthIndex(it) }.contains(month)){
                                TableCellSmall(value)
                            }
                        }

                        IconButton(
                            onClick = {
                                selectedStudentForUpdate = it
                                studentUpdating = true
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

                        IconButton(
                            onClick = {
                                selectedStudentForNote = it
                                studentNoting.value = true
                            }){
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Note",
                                tint = Color.Black
                            )
                        }

                        IconButton(
                            onClick = {
                                selectedStudentForSet = it
                                studentSetting.value = true
                            }){
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Color",
                                tint = Color.DarkGray
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

            if (studentUpdating) {
                selectedStudentForUpdate?.let {
                    StudentUpdateDialog(student = it, onDismiss = { studentUpdating = false }) { updatedStudent ->
                        updateStudent(updatedStudent)
                        studentUpdating = false
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
                    if (isType=="admin"){
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

            if (studentNoting.value) {
                selectedStudentForNote?.let {
                    StudentNoteDialog(student = it, onDismiss = { studentNoting.value = false }) { notedStudent ->
                        updateStudent(notedStudent)
                        studentNoting.value = false
                    }
                }
            }

            if (studentSetting.value) {
                selectedStudentForSet?.let {
                    StudentSettingDialog(student = it, onDismiss = { studentSetting.value = false }) { setStudent ->
                        updateStudent(setStudent)
                        studentSetting.value = false
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

fun getMonthIndex(month: String): Int {
    val normalizedMonth = monthOrder.find { it.startsWith(month, ignoreCase = true) }
    return monthOrder.indexOf(normalizedMonth)
}

fun filterMonths(student: Student): Map<String, String> {
    val filteredMonths = student.month25.entries.dropWhile { it.value.isEmpty() }
    return filteredMonths.associate { it.key to it.value }
}

fun stringToComposeColor(colorString: String): Color {
    return when (colorString) {
        "Color.Black" -> Color.Black
        "Color.White" -> Color.White
        "Color.Gray" -> Color.Gray
        "Color.Red" -> Color.Red
        "Color.Green" -> Color.Blue
        "Color.Blue" -> Color.Green
        "Color.Yellow" -> Color.Yellow
        "Color.Cyan" -> Color.Cyan
        "Color.Magenta" -> Color.Magenta
        else -> Color.Unspecified
    }
}
