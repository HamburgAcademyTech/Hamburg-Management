package az.hamburg.management.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher

@Composable
fun TeacherUpdateDialog(
    teachers: List<Teacher>,
    onDismiss: () -> Unit,
    onSave: (Teacher) -> Unit
){
    var selectedTeacher by remember { mutableStateOf<Teacher?>(null) }
    var selectedTeacherName by remember { mutableStateOf("") }
    var teacherSelecting by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Müəllim düzəliş")
        },
        text = {
            if (teacherSelecting){
                OutlinedTextField(
                    value = selectedTeacherName,
                    onValueChange = { selectedTeacherName = it },
                    label = { Text("Adı") },
                    modifier = Modifier
                        .size(200.dp, 70.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.DarkGray,
                        backgroundColor = Color.White
                    )
                )
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ){
                    items(teachers){
                        Button(
                            modifier = Modifier
                                .padding(5.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    backgroundColor = if (selectedTeacher == it) Color.DarkGray else Color.White,
                                    contentColor = if (selectedTeacher == it) Color.White else Color.Black
                                ),
                            onClick = {
                                selectedTeacher = it
                                teacherSelecting = true
                            },
                            shape = RoundedCornerShape(8.dp)
                        ){
                            Text(it.name)
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Green
                ),onClick = {
                    onSave(
                        selectedTeacher!!
                            .copy(
                                name = selectedTeacherName
                            )
                    )
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Təsdiqlə")
            }
        },
        dismissButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Red
                ),onClick = onDismiss,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Ləğv et")
            }
        },
        contentColor = Color(0xFF2E3B4E),
        shape = RoundedCornerShape(16.dp)
    )
}
