package az.hamburg.management.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import az.hamburg.management.domain.LoginRepository
import az.hamburg.management.domain.StudentRepository
import az.hamburg.management.domain.TeacherRepository
import kotlinx.coroutines.launch

@Composable
fun LoginView(repository: LoginRepository) {
    val scope = rememberCoroutineScope()
    val logins by repository.getLogins().collectAsState(emptyList())
    LoginViewModel(
        logins = logins
    )
}
