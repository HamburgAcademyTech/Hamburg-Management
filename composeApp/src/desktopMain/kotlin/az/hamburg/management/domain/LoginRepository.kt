package az.hamburg.management.domain

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun getLogins(): Flow<List<Login>>
}
