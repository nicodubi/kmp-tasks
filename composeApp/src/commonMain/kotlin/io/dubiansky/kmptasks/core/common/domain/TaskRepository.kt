package io.dubiansky.kmptasks.core.common.domain

import io.dubiansky.kmptasks.core.common.data.model.Task

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
interface TaskRepository {
    suspend fun getTaskList() : List<Task>
}