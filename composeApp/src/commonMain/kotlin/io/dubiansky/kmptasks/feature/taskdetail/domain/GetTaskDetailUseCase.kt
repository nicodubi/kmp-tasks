package io.dubiansky.kmptasks.feature.taskdetail.domain

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class GetTaskDetailUseCase(private val taskRepository: TaskRepository) {
    suspend fun getTaskDetails(taskId: Long): Flow<ResultState<Task?, Nothing>> {
        return taskRepository.getTaskDetails(taskId)
            .map { task -> ResultState.Success(task) }
    }
}
