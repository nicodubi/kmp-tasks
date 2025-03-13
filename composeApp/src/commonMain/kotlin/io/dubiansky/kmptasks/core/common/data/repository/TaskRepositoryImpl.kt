package io.dubiansky.kmptasks.core.common.data.repository

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.data.source.TaskLocalDataSource
import io.dubiansky.kmptasks.core.common.domain.TaskRepository

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class TaskRepositoryImpl(private val taskLocalDataSource: TaskLocalDataSource) : TaskRepository {
    override suspend fun getTaskList(): List<Task> =
        taskLocalDataSource.getTasks()

}