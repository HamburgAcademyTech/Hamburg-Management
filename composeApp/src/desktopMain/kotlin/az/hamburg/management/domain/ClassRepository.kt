package az.hamburg.management.domain

import kotlinx.coroutines.flow.Flow

interface ClassRepository {
    fun getClasses(): Flow<List<Class>>
}
