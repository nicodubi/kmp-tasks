package io.dubiansky.kmptasks.feature.tasklist.domain


import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.common.domain.TaskCreationError
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */

class ChangeCompletedTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun changeTaskCompleted(task: Task): ResultState<Boolean, Nothing> {
        taskRepository.changeTaskCompleted(task)
        return ResultState.Success(true)
    }
}

