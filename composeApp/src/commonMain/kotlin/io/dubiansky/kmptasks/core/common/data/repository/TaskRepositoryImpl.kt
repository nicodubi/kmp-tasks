package io.dubiansky.kmptasks.core.common.data.repository

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.data.source.TaskLocalDataSource
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class TaskRepositoryImpl(private val taskLocalDataSource: TaskLocalDataSource) : TaskRepository {
    override suspend fun getTaskList(): List<Task> =
        taskLocalDataSource.getTasks()

    override suspend fun addTask(taskInput: TaskInput) : Task{
        return taskLocalDataSource.addTask(taskInput)
    }

}