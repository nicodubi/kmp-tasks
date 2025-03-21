package io.dubiansky.kmptasks.core.common.data.repository

import io.dubiansky.kmptasks.core.common.data.database.TaskDao
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.data.model.TaskEntity
import io.dubiansky.kmptasks.core.common.data.source.TaskLocalDataSource
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun getTaskList(): Flow<List<Task>> =
        taskDao.getAllTasks()
            .map { list -> list.map { it.toDomainTask() } }

    override suspend fun addTask(taskInput: TaskInput): Task {
        val taskEntity = taskInput.toEntity()
        taskDao.insertTask(taskEntity)
        return taskEntity.toDomainTask()
    }

    override suspend fun changeTaskCompleted(task: Task) {
        taskDao.insertTask(task.copy(isCompleted = !task.isCompleted).toEntity())
    }

    override suspend fun getTaskDetails(taskId: Long): Flow<Task?> =
        taskDao.getTaskDetails(taskId)
            .map { taskEntity -> taskEntity?.toDomainTask() }

    override suspend fun deleteTask(taskId: Long): Boolean {
        val tasksDeleted = taskDao.deleteTaskById(taskId)
        return tasksDeleted > 0
    }
}

fun TaskEntity.toDomainTask(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        isCompleted = this.isCompleted
    )
}

fun TaskInput.toEntity(): TaskEntity {
    return TaskEntity(
        id = 0, //Room will autogenerate the ID
        title = this.title,
        description = this.description,
        isCompleted = false
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = this.title,
        description = this.description,
        isCompleted = isCompleted
    )
}