package az.hamburg.management.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher

@Composable
fun StudentCopyDialog(
    teachers: List<Teacher>,
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
){
    var selectedTeacherId = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Hansı müəllimə kopyalansın?")
        },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(teachers){
                    Button(
                        modifier = Modifier
                            .padding(5.dp),
                        colors = ButtonDefaults
                            .buttonColors(
                                backgroundColor = if (selectedTeacherId.value == it.id) Color.DarkGray else Color.White,
                                contentColor = if (selectedTeacherId.value == it.id) Color.White else Color.Black
                            ),
                        onClick = {
                            selectedTeacherId.value = it.id
                        },
                        shape = RoundedCornerShape(8.dp)
                    ){
                        Text(it.name)
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
                        student.copy(
                            teacherId = selectedTeacherId.value
                        )
                    )
                },shape = RoundedCornerShape(4.dp)
            ) {
                Text("Kopyala")
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
                Text("Bağla")
            }
        },
        contentColor = Color(0xFF2E3B4E),
        shape = RoundedCornerShape(16.dp)
    )
}
