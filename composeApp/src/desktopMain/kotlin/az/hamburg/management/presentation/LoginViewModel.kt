package az.hamburg.management.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import az.hamburg.management.data.FirebaseStudentRepo
import az.hamburg.management.data.FirebaseTeacherRepo
import az.hamburg.management.domain.Login
import com.russhwolf.settings.*

@Composable
fun LoginViewModel(
    logins: List<Login>
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var warning by remember { mutableStateOf(false) }
    var loggedIn by remember { mutableStateOf(false) }

    if (!loggedIn){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .size(200.dp, 70.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = Color.White
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .size(200.dp, 70.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = Color.White
                )
            )

            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    val containsLogin = logins.find { it.username == username && it.password == password }
                    if (containsLogin != null){
                        type = containsLogin.type
                        warning = false
                        loggedIn = true
                    }else{
                        warning = true
                    }
                }){
                Text(
                    text = "Login",
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            if (warning){
                Text(
                    text = "İstifadəçi adı və ya şifrə yanlışdır",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }else{
        val teacherRepo = remember { FirebaseTeacherRepo() }
        val studentRepo = remember { FirebaseStudentRepo() }
        TeachersView(repository = teacherRepo, repository2 = studentRepo, isType = type)
    }
}
