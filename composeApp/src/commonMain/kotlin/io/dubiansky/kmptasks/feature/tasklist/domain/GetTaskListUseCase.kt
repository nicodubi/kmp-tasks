package io.dubiansky.kmptasks.feature.tasklist.domain

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.TaskRepository

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class GetTaskListUseCase(private val taskRepository: TaskRepository) {
    suspend fun getTaskList(): List<Task> =
        taskRepository.getTaskList()

}