package io.dubiansky.kmptasks.feature.tasklist.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dubiansky.kmptasks.feature.tasklist.domain.GetTaskListUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.Error
import io.dubiansky.kmptasks.core.common.domain.GeneralError
import io.dubiansky.kmptasks.core.common.domain.ResultState

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */


class TaskListViewModel(private val getTaskListUseCase: GetTaskListUseCase) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error: Error? by mutableStateOf(null)
        private set

    var tasks: List<Task> by mutableStateOf(emptyList())
        private set


    init {
        loadTasks()
    }

    private fun loadTasks() {
        isLoading = true
        error = null

        viewModelScope.launch {
            getTaskListUseCase.getTaskList()
                .collect { result ->
                    when (result) {
                        is ResultState.Success -> tasks = result.data
                        is ResultState.Failure -> error = GeneralError.SOMETHING_WENT_WRONG
                    }
                    isLoading = false
                }
        }
    }

}