package az.hamburg.management.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import az.hamburg.management.domain.Student
import az.hamburg.management.presentation.sortMonthData

@Composable
fun StudentUpdateDialog(
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
) {
    var updatingYear by remember { mutableStateOf(student.months.keys.first()) }
    var name by remember { mutableStateOf(student.name) }
    var number by remember { mutableStateOf(student.number) }
    var cost by remember { mutableStateOf(student.cost) }
    var startDate by remember { mutableStateOf(student.startDate) }
    var level by remember { mutableStateOf(student.level) }
    var day by remember { mutableStateOf(student.day) }
    var hour by remember { mutableStateOf(student.hour) }

    var jan by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[0].second) }
    var feb by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[1].second) }
    var mar by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[2].second) }
    var apr by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[3].second) }
    var may by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[4].second) }
    var jun by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[5].second) }
    var jul by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[6].second) }
    var aug by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[7].second) }
    var sep by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[8].second) }
    var oct by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[9].second) }
    var nov by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[10].second) }
    var dec by remember { mutableStateOf(sortMonthData(student.months[updatingYear] ?: emptyMap())[11].second) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tələbə düzəliş") },
        text = {
            Column {
                Row {
                    TextButton(
                        onClick = {
                            updatingYear = "2024"
                            jan = sortMonthData(student.months[updatingYear] ?: emptyMap())[0].second
                            feb = sortMonthData(student.months[updatingYear] ?: emptyMap())[1].second
                            mar = sortMonthData(student.months[updatingYear] ?: emptyMap())[2].second
                            apr = sortMonthData(student.months[updatingYear] ?: emptyMap())[3].second
                            may = sortMonthData(student.months[updatingYear] ?: emptyMap())[4].second
                            jun = sortMonthData(student.months[updatingYear] ?: emptyMap())[5].second
                            jul = sortMonthData(student.months[updatingYear] ?: emptyMap())[6].second
                            aug = sortMonthData(student.months[updatingYear] ?: emptyMap())[7].second
                            sep = sortMonthData(student.months[updatingYear] ?: emptyMap())[8].second
                            oct = sortMonthData(student.months[updatingYear] ?: emptyMap())[9].second
                            nov = sortMonthData(student.months[updatingYear] ?: emptyMap())[10].second
                            dec = sortMonthData(student.months[updatingYear] ?: emptyMap())[11].second
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = if (updatingYear == "2024") Color.DarkGray else Color.Gray
                        )
                    ){
                        Text("2024")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            updatingYear = "2025"
                            jan = sortMonthData(student.months[updatingYear] ?: emptyMap())[0].second
                            feb = sortMonthData(student.months[updatingYear] ?: emptyMap())[1].second
                            mar = sortMonthData(student.months[updatingYear] ?: emptyMap())[2].second
                            apr = sortMonthData(student.months[updatingYear] ?: emptyMap())[3].second
                            may = sortMonthData(student.months[updatingYear] ?: emptyMap())[4].second
                            jun = sortMonthData(student.months[updatingYear] ?: emptyMap())[5].second
                            jul = sortMonthData(student.months[updatingYear] ?: emptyMap())[6].second
                            aug = sortMonthData(student.months[updatingYear] ?: emptyMap())[7].second
                            sep = sortMonthData(student.months[updatingYear] ?: emptyMap())[8].second
                            oct = sortMonthData(student.months[updatingYear] ?: emptyMap())[9].second
                            nov = sortMonthData(student.months[updatingYear] ?: emptyMap())[10].second
                            dec = sortMonthData(student.months[updatingYear] ?: emptyMap())[11].second
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = if (updatingYear == "2025") Color.DarkGray else Color.Gray
                        )
                    ){
                        Text("2025")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            updatingYear = "2026"
                            jan = sortMonthData(student.months[updatingYear] ?: emptyMap())[0].second
                            feb = sortMonthData(student.months[updatingYear] ?: emptyMap())[1].second
                            mar = sortMonthData(student.months[updatingYear] ?: emptyMap())[2].second
                            apr = sortMonthData(student.months[updatingYear] ?: emptyMap())[3].second
                            may = sortMonthData(student.months[updatingYear] ?: emptyMap())[4].second
                            jun = sortMonthData(student.months[updatingYear] ?: emptyMap())[5].second
                            jul = sortMonthData(student.months[updatingYear] ?: emptyMap())[6].second
                            aug = sortMonthData(student.months[updatingYear] ?: emptyMap())[7].second
                            sep = sortMonthData(student.months[updatingYear] ?: emptyMap())[8].second
                            oct = sortMonthData(student.months[updatingYear] ?: emptyMap())[9].second
                            nov = sortMonthData(student.months[updatingYear] ?: emptyMap())[10].second
                            dec = sortMonthData(student.months[updatingYear] ?: emptyMap())[11].second
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = if (updatingYear == "2026") Color.DarkGray else Color.Gray
                        )
                    ){
                        Text("2026")
                    }
                }
                Row {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = number,
                        onValueChange = { number = it },
                        label = { Text("Number") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = cost,
                        onValueChange = { cost = it },
                        label = { Text("Cost") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    OutlinedTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        label = { Text("Start Date") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = level,
                        onValueChange = { level = it },
                        label = { Text("Level") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = day,
                        onValueChange = { day = it },
                        label = { Text("Day") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = hour,
                        onValueChange = { hour = it },
                        label = { Text("Hour") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }

                Row {
                    OutlinedTextField(
                        value = jan,
                        onValueChange = { jan = it },
                        label = { Text("Yan") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = feb,
                        onValueChange = { feb = it },
                        label = { Text("Fev") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = mar,
                        onValueChange = { mar = it },
                        label = { Text("Mar") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = apr,
                        onValueChange = { apr = it },
                        label = { Text("Apr") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = may,
                        onValueChange = { may = it },
                        label = { Text("May") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = jun,
                        onValueChange = { jun = it },
                        label = { Text("Iyn") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                Row {
                    OutlinedTextField(
                        value = jul,
                        onValueChange = { jul = it },
                        label = { Text("Iyl") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = aug,
                        onValueChange = { aug = it },
                        label = { Text("Avq") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = sep,
                        onValueChange = { sep = it },
                        label = { Text("Sen") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = oct,
                        onValueChange = { oct = it },
                        label = { Text("Okt") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = nov,
                        onValueChange = { nov = it },
                        label = { Text("Noy") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = dec,
                        onValueChange = { dec = it },
                        label = { Text("Dek") },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                // Create an updated student object with the edited values
                onSave(
                    student.copy(
                        name = name,
                        number = number,
                        cost = cost,
                        startDate = startDate,
                        level = level,
                        day = day,
                        hour = hour,
                        months = student.months.toMutableMap().apply {
                            this[updatingYear] = mapOf(
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[0].first.lowercase() to jan,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[1].first.lowercase() to feb,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[2].first.lowercase() to mar,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[3].first.lowercase() to apr,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[4].first.lowercase() to may,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[5].first.lowercase() to jun,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[6].first.lowercase() to jul,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[7].first.lowercase() to aug,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[8].first.lowercase() to sep,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[9].first.lowercase() to oct,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[10].first.lowercase() to nov,
                                sortMonthData(student.months[updatingYear] ?: emptyMap())[11].first.lowercase() to dec
                            )
                        }
                    )
                )
            },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Green
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Təsdiqlə")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Red
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Ləğv et")
            }
        },
        contentColor = Color(0xFF2E3B4E),
        shape = RoundedCornerShape(16.dp)
    )
}
