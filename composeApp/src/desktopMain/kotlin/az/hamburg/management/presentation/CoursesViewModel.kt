package az.hamburg.management.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
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
import az.hamburg.management.data.FirebaseClassRepo
import az.hamburg.management.data.FirebaseCourseRepo
import az.hamburg.management.domain.Course
import coil3.compose.AsyncImage

@Composable
fun CoursesViewModel(
    courses: List<Course>,
){
    val classRepo = remember { FirebaseClassRepo() }
    var currentCourse = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (currentCourse.value.isNotEmpty()){
            Button(modifier = Modifier
                .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color.Black),
                onClick = {
                currentCourse.value = ""
            }){
                Text(
                    text = "< Back",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            ClassesView(repository = classRepo, currentCourse = currentCourse.value)
        }else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(4)
            ) {
                items(courses){it ->
                    Button(modifier = Modifier
                        .padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFF0000),
                            contentColor = Color.White),
                        onClick = {
                            currentCourse.value = it.id
                        }
                    ){
                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            AsyncImage(
                                model = it.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 180.dp, height = 180.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = it.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = it.id,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
