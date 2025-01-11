package az.hamburg.management.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.hamburg.management.domain.Student
import az.hamburg.management.domain.Teacher
import az.hamburg.management.view.ColorPicker

@Composable
fun StudentSettingDialog(
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
){
    var bg by remember { mutableStateOf(student.bg) }
    var fg by remember { mutableStateOf(student.fg) }
    var colorsList = listOf(
        Color.Black,
        Color.White,
        Color.Gray,
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta
    )
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Tələbə settings")
        },
        text = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .width(300.dp)
                        .height(150.dp)
                ){
                    items(colorsList){
                        TextButton(
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .background(it)
                                .padding(5.dp),
                            onClick = {
                                bg = composeColorToString(it)
                            }
                        ){
                            Text("")
                        }
                    }
                }

                Column {
                    TextButton(
                        onClick = {
                            fg = "bold"
                        }
                    ){
                        Text(
                            text = "Bold",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    TextButton(
                        onClick = {
                            fg = "italic"
                        }
                    ){
                        Text(
                            text = "Italic",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic,
                        )
                    }
                    TextButton(
                        onClick = {
                            fg = "underline"
                        }
                    ){
                        Text(
                            text = "Underlined",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                            textDecoration = TextDecoration.Underline,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                TextButton(
                    onClick = {
                        bg = ""
                        fg = ""
                    }
                ){
                    Text(
                        text = "Sıfırla",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace
                    )
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
                            bg = bg,
                            fg = fg
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

fun composeColorToString(color: Color): String {
    return when (color) {
        Color.Black -> "Color.Black"
        Color.White -> "Color.White"
        Color.Gray -> "Color.Gray"
        Color.Red -> "Color.Red"
        Color.Green -> "Color.Blue"
        Color.Blue -> "Color.Green"
        Color.Yellow -> "Color.Yellow"
        Color.Cyan -> "Color.Cyan"
        Color.Magenta -> "Color.Magenta"
        else -> ""
    }
}
