package az.hamburg.management.popup

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.hamburg.management.domain.Student

@Composable
fun StudentDeleteDialog(
    name: String,
    onDismiss: () -> Unit,
    onSave: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = name)
        },
        text = {
            Text("Silinsin ?")
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Green
                ),onClick = onSave,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Bəli")
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
                Text("Xeyr")
            }
        },
        contentColor = Color(0xFF2E3B4E),
        shape = RoundedCornerShape(16.dp)
    )
}
