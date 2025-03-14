package io.dubiansky.kmptasks.feature.addtask.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput
import io.dubiansky.kmptasks.feature.addtask.domain.AddTaskUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.dubiansky.kmptasks.core.common.domain.Error
import io.dubiansky.kmptasks.core.common.domain.GeneralError
import io.dubiansky.kmptasks.core.common.domain.ResultState
import io.dubiansky.kmptasks.core.common.domain.TaskCreationError
import org.jetbrains.compose.resources.StringResource

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */
class AddTaskViewModel(private val addTaskUseCase: AddTaskUseCase) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error: Error? by mutableStateOf(null)
        private set

    var taskAdded: Task? by mutableStateOf(null)
        private set

    fun addTask(taskInput: TaskInput) {
        isLoading = true
        error = null
        taskAdded = null

        viewModelScope.launch {
            val result = addTaskUseCase.addTask(taskInput)

            when (result) {
                is ResultState.Success -> taskAdded = result.data
                is ResultState.Failure -> error =
                    result.errorCause
            }

            isLoading = false
        }
    }
}