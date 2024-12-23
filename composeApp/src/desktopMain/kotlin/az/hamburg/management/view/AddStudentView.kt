package az.hamburg.management.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import az.hamburg.management.custom_view.StudentFieldLarge
import az.hamburg.management.custom_view.StudentFieldMedium
import az.hamburg.management.custom_view.StudentFieldSmall
import az.hamburg.management.domain.Student

@Composable
fun AddStudent(
    teacherId: String,
    addStudent: (Student) -> Unit
){
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var hour by remember { mutableStateOf("") }
    var jan by remember { mutableStateOf("") }
    var feb by remember { mutableStateOf("") }
    var mar by remember { mutableStateOf("") }
    var apr by remember { mutableStateOf("") }
    var may by remember { mutableStateOf("") }
    var jun by remember { mutableStateOf("") }
    var jul by remember { mutableStateOf("") }
    var aug by remember { mutableStateOf("") }
    var sep by remember { mutableStateOf("") }
    var oct by remember { mutableStateOf("") }
    var nov by remember { mutableStateOf("") }
    var dec by remember { mutableStateOf("") }

    Column {
        Row {
            Spacer(
                modifier = Modifier
                    .size(70.dp, 60.dp)
            )
            StudentFieldLarge(
                value = name,
                onValueChange = { name = it },
                label = "Ad, Soyad",
                placeholder = "Ad, Soyad"
            )
            StudentFieldLarge(
                value = number,
                onValueChange = { number = it },
                label = "Nömrə",
                placeholder = "Nömrə"
            )
            StudentFieldMedium(
                value = cost,
                onValueChange = { cost = it },
                label = "Ödəniş",
                placeholder = "Ödəniş"
            )
            StudentFieldMedium(
                value = startDate,
                onValueChange = { startDate = it },
                label = "Başlama",
                placeholder = "Başlama"
            )
            StudentFieldMedium(
                value = level,
                onValueChange = { level = it },
                label = "Səviyyə",
                placeholder = "Səviyyə"
            )
            StudentFieldMedium(
                value = day,
                onValueChange = { day = it },
                label = "Gün",
                placeholder = "Gün"
            )
            StudentFieldMedium(
                value = hour,
                onValueChange = { hour = it },
                label = "Saat",
                placeholder = "Saat"
            )
            StudentFieldSmall(
                value = jan,
                onValueChange = { jan = it },
                label = "Yan",
                placeholder = "Yan"
            )
            StudentFieldSmall(
                value = feb,
                onValueChange = { feb = it },
                label = "Fev",
                placeholder = "Fev"
            )
            StudentFieldSmall(
                value = mar,
                onValueChange = { mar = it },
                label = "Mar",
                placeholder = "Mar"
            )
            StudentFieldSmall(
                value = apr,
                onValueChange = { apr = it },
                label = "Apr",
                placeholder = "Apr"
            )
            StudentFieldSmall(
                value = may,
                onValueChange = { may = it },
                label = "May",
                placeholder = "May"
            )
            StudentFieldSmall(
                value = jun,
                onValueChange = { jun = it },
                label = "Iyn",
                placeholder = "Iyn"
            )
            StudentFieldSmall(
                value = jul,
                onValueChange = { jul = it },
                label = "Iyl",
                placeholder = "Iyl"
            )
            StudentFieldSmall(
                value = aug,
                onValueChange = { aug = it },
                label = "Avq",
                placeholder = "Avq"
            )
            StudentFieldSmall(
                value = sep,
                onValueChange = { sep = it },
                label = "Sen",
                placeholder = "Sen"
            )
            StudentFieldSmall(
                value = oct,
                onValueChange = { oct = it },
                label = "Okt",
                placeholder = "Okt"
            )
            StudentFieldSmall(
                value = nov,
                onValueChange = { nov = it },
                label = "Noy",
                placeholder = "Noy"
            )
            StudentFieldSmall(
                value = dec,
                onValueChange = { dec = it },
                label = "Dek",
                placeholder = "Dek"
            )

            Button(
                modifier = Modifier
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF000000),
                    contentColor = Color.White),
                onClick = {
                    val student = Student(
                        id = "",
                        teacherId = teacherId,
                        name = name,
                        number = number,
                        cost = cost,
                        startDate = startDate,
                        level = level,
                        day = day,
                        hour = hour,
                        month = mapOf(
                            Pair("january", jan),
                            Pair("february", feb),
                            Pair("march", mar),
                            Pair("april", apr),
                            Pair("may", may),
                            Pair("june", jun),
                            Pair("july", jul),
                            Pair("august", aug),
                            Pair("september", sep),
                            Pair("october", oct),
                            Pair("november", nov),
                            Pair("december", dec)
                        ),
                        isDeleted = false,
                        notes = ""
                    )
                    addStudent(student)
                }){
                Text(
                    text = "Əlavə et",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
