package io.dubiansky.kmptasks.feature.tasklist.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.feature.tasklist.domain.GetTaskListUseCase
import kotlinx.coroutines.launch

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */


class TaskListViewModel(private val getTaskListUseCase: GetTaskListUseCase) : ViewModel() {

    private val _taskListUiState: MutableState<TaskListUiState> = mutableStateOf(TaskListUiState())
    val taskListUiState: State<TaskListUiState> = _taskListUiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _taskListUiState.value = _taskListUiState.value.copy(isLoading = true, error = null)
            try {
                val tasks = getTaskListUseCase.getTaskList()
                _taskListUiState.value = TaskListUiState(tasks = tasks)
            } catch (e: Exception) {
                _taskListUiState.value = _taskListUiState.value.copy(isLoading = false, error = e)
            }
        }

    }
}