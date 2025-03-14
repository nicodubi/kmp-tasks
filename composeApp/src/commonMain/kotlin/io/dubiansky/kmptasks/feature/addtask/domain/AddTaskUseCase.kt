package io.dubiansky.kmptasks.feature.addtask.domain


import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.common.domain.TaskCreationError
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */

class AddTaskUseCase(private val taskRepository: TaskRepository) {
    private val MAX_TITLE_LENGTH = 50

    suspend fun addTask(taskInput: TaskInput): ResultState<Task, TaskCreationError> {
        with(taskInput.title) {
            if (isBlank()) return ResultState.Failure(TaskCreationError.TITLE_IS_BLANK)
            if (length > MAX_TITLE_LENGTH) return ResultState.Failure(TaskCreationError.TITLE_IS_TOO_LONG)
        }

        val task = taskRepository.addTask(taskInput)
        return ResultState.Success(task)
    }
}

