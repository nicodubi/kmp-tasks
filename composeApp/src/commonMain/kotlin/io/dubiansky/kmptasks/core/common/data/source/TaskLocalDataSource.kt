package io.dubiansky.kmptasks.core.common.data.source

import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput
import kotlinx.coroutines.delay

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
class TaskLocalDataSource {

    val tasks = mutableListOf<Task>()

    suspend fun getTasks(): List<Task> {
        //TODO fake database request until Room implementation
        delay(1000)
        if (tasks.isNotEmpty()) {
            return tasks
        }

        val tasksListFake = (1..15).map {
            Task(
                id = it.toLong(),
                title = "Task $it",
                description = "Description $it",
                isCompleted = it % 2 == 0
            )
        }
        tasks.addAll(tasksListFake)
        return tasks
    }

    suspend fun addTask(taskInput: TaskInput): Task {
        //TODO fake database request until Room implementation
        delay(1500)
        val task =
            Task(title = taskInput.title, description = taskInput.description, id = (tasks.size + 1).toLong())
        tasks.add(task)
        return task
    }


}