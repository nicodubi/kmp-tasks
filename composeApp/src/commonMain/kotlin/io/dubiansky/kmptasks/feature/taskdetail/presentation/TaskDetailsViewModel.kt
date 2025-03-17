package io.dubiansky.kmptasks.feature.taskdetail.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.Error
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.navigation.TaskDetailsRoute
import io.dubiansky.kmptasks.feature.taskdetail.domain.GetTaskDetailUseCase
import io.dubiansky.kmptasks.feature.tasklist.domain.ChangeCompletedTaskUseCase
import io.dubiansky.kmptasks.feature.tasklist.domain.DeleteTaskUseCase

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */


class TaskDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getTaskDetailUseCase: GetTaskDetailUseCase,
    private val changeCompletedTaskUseCase: ChangeCompletedTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error: Error? by mutableStateOf(null)
        private set

    var task: Task? by mutableStateOf(null)
        private set

    var taskDeleted by mutableStateOf(false)
        private set


    init {
        val args = savedStateHandle.toRoute<TaskDetailsRoute>()
        val taskId = args.taskId
        loadTask(taskId)
    }

    private fun loadTask(taskId: Long) {
        isLoading = true

        viewModelScope.launch {
            getTaskDetailUseCase.getTaskDetails(taskId = taskId)
                .collect { result ->
                    if (result is ResultState.Success) {
                        task = result.data
                    }
                    isLoading = false
                }
        }
    }

    fun onChangeCompletedTask(task: Task) {

        viewModelScope.launch {
            changeCompletedTaskUseCase.changeTaskCompleted(task)
        }

    }

    fun onDeleteTask(task: Task) {
        isLoading = true
        error = null
        viewModelScope.launch {
            val result = deleteTaskUseCase.deleteTask(task)
            when (result) {
                is ResultState.Success -> {
                    taskDeleted = true
                }

                is ResultState.Failure -> {
                    error = result.errorCause

                }
            }
            isLoading = false
        }
    }

}