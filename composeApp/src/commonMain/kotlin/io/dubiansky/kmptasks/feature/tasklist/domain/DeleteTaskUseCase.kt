package io.dubiansky.kmptasks.feature.tasklist.domain

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.GeneralError
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun deleteTask(task: Task): ResultState<Unit, GeneralError> {
        val deleteTaskResult = taskRepository.deleteTask(task.id)
        if (deleteTaskResult) {
            return ResultState.Success(Unit)
        } else {
            return ResultState.Failure(GeneralError.SOMETHING_WENT_WRONG)
        }
    }
}
