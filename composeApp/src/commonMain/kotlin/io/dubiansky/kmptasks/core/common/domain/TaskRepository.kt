package io.dubiansky.kmptasks.core.common.domain

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput
import kotlinx.coroutines.flow.Flow

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
interface TaskRepository {
    suspend fun getTaskList(): Flow<List<Task>>
    suspend fun addTask(taskInput: TaskInput) : Task
}