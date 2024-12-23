package az.hamburg.management.domain

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
    val type: String
)
