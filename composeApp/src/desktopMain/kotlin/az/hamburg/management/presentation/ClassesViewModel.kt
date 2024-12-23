package az.hamburg.management.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.hamburg.management.domain.Class
import az.hamburg.management.domain.Course
import az.hamburg.management.domain.StudentC
import coil3.compose.AsyncImage

@Composable
fun ClassesViewModel(
    classes: List<Class>,
    currentCourse: String,
){
    var goDetail = remember { mutableStateOf(false) }
    var currentClass = remember { mutableStateOf(Class("", "")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        for (i in classes){
            if (i.course_id == currentCourse){
                //teacher.value = i.teacher
                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black),
                    onClick = {
                        goDetail.value = true
                        currentClass.value = i
                }){
                    Text(
                        text = "Müəllim: ${i.teacher}"
                    )
                }
            }
        }

        if (goDetail.value){
            ClassDetail(
                studentList = currentClass.value.studentList
            )
        }
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

@Composable
fun ClassDetail(
    studentList: List<StudentC>
){
    ClassHeader()

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(5.dp)
            //.wrapContentSize(align = Alignment.Center)
    ) {
        items(studentList){
            Row {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .size(width = 100.dp, height = 20.dp)
                )
                Text(
                    text = it.cost,
                    modifier = Modifier
                        .size(width = 100.dp, height = 20.dp)
                )
                Text(
                    text = it.start_date,
                    modifier = Modifier
                        .size(width = 100.dp, height = 20.dp)
                )

                val x = sortMonthData(it.month)
                // Display month data
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(x) { (month, value) ->
                        Text(
                            text = value,
                            //style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .size(width = 43.dp, height = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClassHeader(){
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
    ) {
        Text(
            text = "Ad",
            modifier = Modifier
                .size(width = 100.dp, height = 20.dp)
        )
        Text(
            text = "Ödəniş",
            modifier = Modifier
                .size(width = 100.dp, height = 20.dp)
        )
        Text(
            text = "Başlama",
            modifier = Modifier
                .size(width = 100.dp, height = 20.dp)
        )
        Text(
            text = "Yan",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Fev",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Mar",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Apr",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "May",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Iyn",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Iyl",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Avq",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Sen",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Okt",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Noy",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
        Text(
            text = "Dek",
            modifier = Modifier
                .size(width = 43.dp, height = 20.dp)
        )
    }
}
