package io.dubiansky.kmptasks.core.common.data.source

import io.dubiansky.kmptasks.core.common.data.model.Task
import kotlinx.coroutines.delay

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class TaskLocalDataSource {

    suspend fun getTasks(): List<Task> {
        //TODO fake database request until Room implementation
        delay(1000)
        return (1..15).map {
            Task(
                id = it,
                title = "Task $it",
                description = "Description $it",
                isCompleted = it % 2 == 0
            )
        }
    }
}