package az.hamburg.management.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.hamburg.management.data.FirebaseStudentITRepo
import az.hamburg.management.data.FirebaseStudentRepo
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher
import az.hamburg.management.popup.TeacherAddDialog
import az.hamburg.management.popup.TeacherDeleteDialog
import az.hamburg.management.popup.TeacherUpdateDialog
import hamburgmanagement.composeapp.generated.resources.Res
import hamburgmanagement.composeapp.generated.resources.card_bg
import hamburgmanagement.composeapp.generated.resources.card_bg_it
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeachersViewModel(
    teachers: List<Teacher>,
    addTeacher: (Teacher) -> Unit,
    updateTeacher: (Teacher) -> Unit,
    deleteTeacher: (Teacher) -> Unit,
    students: List<Student>,
    deleteStudent: (Student) -> Unit,
    isType: String
){
    var teacherAdding by remember { mutableStateOf(false) }
    var teacherDeleting by remember { mutableStateOf(false) }
    var teacherUpdating by remember { mutableStateOf(false) }
    var goStudents by remember { mutableStateOf(false) }
    var selectedTeacherId by remember { mutableStateOf("") }
    var selectedTeacherName by remember { mutableStateOf("") }
    var isCalculating by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var startMonth by remember { mutableStateOf("january") }
    var endMonth by remember { mutableStateOf("december") }
    var startMonthIndex by remember { mutableStateOf(0) }
    var endMonthIndex by remember { mutableStateOf(11) }
    val months = listOf(
        "yanvar", "fevral", "mart", "aprel", "may", "iyun",
        "iyul", "avqust", "sentyabr", "oktyabr", "noyabr", "dekabr"
    )

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        val studentRepo = remember { FirebaseStudentRepo() }
        val studentITRepo = remember { FirebaseStudentITRepo() }
        if (goStudents){
            Button(
                modifier = Modifier
                .padding(top = 10.dp, start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black),
                onClick = {
                    goStudents = false
            }){
                Text(
                    text = "< Back"
                )
            }

            StudentsView(
                repository = if (isType == "it") studentITRepo else studentRepo,
                teacherId = selectedTeacherId,
                teacherName = selectedTeacherName,
                teachers = teachers,
                isType = isType
            )
        }else{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Giriş: $isType",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Müəllimlər",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        teacherDeleting = true
                    }){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colors.error,
                        modifier = Modifier.size(50.dp, 50.dp)
                    )
                }
                IconButton(
                    onClick = {
                        teacherUpdating = true
                    }){
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(50.dp, 50.dp)
                    )
                }
                IconButton(
                    onClick = {
                        teacherAdding = true
                    }){
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add",
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.size(50.dp, 50.dp)
                    )
                }
            }

            if (teacherAdding){
                TeacherAddDialog(onDismiss = {
                    teacherAdding = false
                }){
                    addTeacher(it)
                    teacherAdding = false
                }
            }

            if (teacherDeleting){
                TeacherDeleteDialog(teachers = teachers, onDismiss = {
                    teacherDeleting = false
                }){
                    deleteTeacher(it)
                    students.forEach{ st ->
                        if (st.teacherId == it.id){
                            deleteStudent(st)
                        }
                    }
                    teacherDeleting = false
                }
            }

            if (teacherUpdating){
                TeacherUpdateDialog(teachers = teachers, onDismiss = {
                    teacherUpdating = false
                }){
                    updateTeacher(it)
                    teacherUpdating = false
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(teachers){
                    Box(
                        modifier = Modifier
                            .clickable {
                                goStudents  = true
                                selectedTeacherId = it.id
                                selectedTeacherName = it.name
                            }
                            .fillMaxWidth()
                            .weight(1f)
                            .height(150.dp)
                            .wrapContentSize(align = Alignment.Center)
                            .padding(20.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(if (isType=="it") Res.drawable.card_bg_it else Res.drawable.card_bg),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize(),
                        )

                        Text(
                            text = it.name,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            //REPORTING
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (isCalculating){
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    ) {
                        val activeStudents = students.filter { !it.isDeleted }

                        Text(
                            text = "Müəllim sayı: ${teachers.count()}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Tələbə sayı: ${activeStudents.count()}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Aylıq ödəniş: ${activeStudents.sumOf { it.cost.toIntOrNull() ?: 0 }}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Büdcə: ${calculateCostNew(activeStudents)}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                TextButton(onClick = {
                    isCalculating = !isCalculating
                }){
                    Text(
                        text = if (isCalculating) "Bağla" else "Hesabla",
                        color = if (isCalculating) Color.Red else Color.Green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

fun calculateTotalCost(items: List<Student>, startMonth: String, endMonth: String): Int {
    val monthsOrder = listOf(
        "january", "february", "march", "april", "may", "june",
        "july", "august", "september", "october", "november", "december"
    )

    // Ensure valid range
    val startIndex = monthsOrder.indexOf(startMonth)
    val endIndex = monthsOrder.indexOf(endMonth)

    if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
        throw IllegalArgumentException("Invalid month range: $startMonth to $endMonth")
    }

    // Select the months in the interval
    val selectedMonths = monthsOrder.subList(startIndex, endIndex + 1)

    return items.sumOf { item ->
        val monthlyCost = item.cost.toIntOrNull() ?: 0
        val validMonthsCount = selectedMonths.count { month ->
            item.months[month]?.isNotEmpty() == true
        }
        monthlyCost * validMonthsCount
    }
}

fun calculateCostNew(dataList: List<Student>) : Int{
    var totalSum = 0

    for (entry in dataList) {
        val cost = entry.cost.toIntOrNull() ?: 0
        val monthsData = entry.months as? Map<String, Map<String, String>> ?: emptyMap()

        val count = monthsData.values
            .flatMap { it.values }
            .count { it.isNotEmpty() }

        totalSum += cost * count
    }

    return totalSum
}

/*
{calculateTotalCost(activeStudents, startMonth, endMonth)}

Row {
                            TextButton(onClick = {
                                expanded1 = true
                            }){
                                Text(
                                    text = startMonth,
                                    color = Color.DarkGray,
                                    fontSize = 18.sp
                                )
                            }
                            DropdownMenu(
                                expanded = expanded1,
                                onDismissRequest = { expanded1 = false }
                            ) {
                                months.slice(0 until endMonthIndex+1).forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            startMonthIndex = index
                                            startMonth = when (item) {
                                                "yanvar" -> "january"
                                                "fevral" -> "february"
                                                "mart" -> "march"
                                                "aprel" -> "april"
                                                "may" -> "may"
                                                "iyun" -> "june"
                                                "iyul" -> "july"
                                                "avqust" -> "august"
                                                "sentyabr" -> "september"
                                                "oktyabr" -> "october"
                                                "noyabr" -> "november"
                                                "dekabr" -> "december"
                                                else -> "unknown"
                                            }

                                            expanded1 = false
                                        }
                                    ) {
                                        Text(text = item)
                                    }
                                }
                            }
                            TextButton(onClick = {
                                expanded2 = true
                            }){
                                Text(
                                    text = endMonth,
                                    color = Color.DarkGray,
                                    fontSize = 18.sp
                                )
                            }
                            DropdownMenu(
                                expanded = expanded2,
                                onDismissRequest = { expanded2 = false }
                            ) {
                                months.slice(startMonthIndex until months.size).forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            endMonthIndex = index
                                            endMonth = when (item) {
                                                "yanvar" -> "january"
                                                "fevral" -> "february"
                                                "mart" -> "march"
                                                "aprel" -> "april"
                                                "may" -> "may"
                                                "iyun" -> "june"
                                                "iyul" -> "july"
                                                "avqust" -> "august"
                                                "sentyabr" -> "september"
                                                "oktyabr" -> "october"
                                                "noyabr" -> "november"
                                                "dekabr" -> "december"
                                                else -> "unknown"
                                            }

                                            expanded2 = false
                                        }
                                    ) {
                                        Text(text = item)
                                    }
                                }
                            }
                        }
 */
