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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.hamburg.management.domain.Student
import az.hamburg.management.presentation.sortMonthData

@Composable
fun StudentUpdateDialog(
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
) {
    var name by remember { mutableStateOf(student.name) }
    var number by remember { mutableStateOf(student.number) }
    var cost by remember { mutableStateOf(student.cost) }
    var startDate by remember { mutableStateOf(student.startDate) }
    var level by remember { mutableStateOf(student.level) }
    var day by remember { mutableStateOf(student.day) }
    var hour by remember { mutableStateOf(student.hour) }
    var jan by remember { mutableStateOf(sortMonthData(student.month)[0].second) }
    var feb by remember { mutableStateOf(sortMonthData(student.month)[1].second) }
    var mar by remember { mutableStateOf(sortMonthData(student.month)[2].second) }
    var apr by remember { mutableStateOf(sortMonthData(student.month)[3].second) }
    var may by remember { mutableStateOf(sortMonthData(student.month)[4].second) }
    var jun by remember { mutableStateOf(sortMonthData(student.month)[5].second) }
    var jul by remember { mutableStateOf(sortMonthData(student.month)[6].second) }
    var aug by remember { mutableStateOf(sortMonthData(student.month)[7].second) }
    var sep by remember { mutableStateOf(sortMonthData(student.month)[8].second) }
    var oct by remember { mutableStateOf(sortMonthData(student.month)[9].second) }
    var nov by remember { mutableStateOf(sortMonthData(student.month)[10].second) }
    var dec by remember { mutableStateOf(sortMonthData(student.month)[11].second) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tələbə düzəliş") },
        text = {
            Column {
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = feb,
                        onValueChange = { feb = it },
                        label = { Text("Fev") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = mar,
                        onValueChange = { mar = it },
                        label = { Text("Mar") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = apr,
                        onValueChange = { apr = it },
                        label = { Text("Apr") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = may,
                        onValueChange = { may = it },
                        label = { Text("May") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = jun,
                        onValueChange = { jun = it },
                        label = { Text("Iyn") },
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = aug,
                        onValueChange = { aug = it },
                        label = { Text("Avq") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = sep,
                        onValueChange = { sep = it },
                        label = { Text("Sen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = oct,
                        onValueChange = { oct = it },
                        label = { Text("Okt") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = nov,
                        onValueChange = { nov = it },
                        label = { Text("Noy") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    OutlinedTextField(
                        value = dec,
                        onValueChange = { dec = it },
                        label = { Text("Dek") },
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
                        month = mapOf(
                            Pair(sortMonthData(student.month)[0].first.lowercase(), jan),
                            Pair(sortMonthData(student.month)[1].first.lowercase(), feb),
                            Pair(sortMonthData(student.month)[2].first.lowercase(), mar),
                            Pair(sortMonthData(student.month)[3].first.lowercase(), apr),
                            Pair(sortMonthData(student.month)[4].first.lowercase(), may),
                            Pair(sortMonthData(student.month)[5].first.lowercase(), jun),
                            Pair(sortMonthData(student.month)[6].first.lowercase(), jul),
                            Pair(sortMonthData(student.month)[7].first.lowercase(), aug),
                            Pair(sortMonthData(student.month)[8].first.lowercase(), sep),
                            Pair(sortMonthData(student.month)[9].first.lowercase(), oct),
                            Pair(sortMonthData(student.month)[10].first.lowercase(), nov),
                            Pair(sortMonthData(student.month)[11].first.lowercase(), dec)
                        )
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
