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

/**
 * Alternative implementation following the MVI architecture pattern.
 * (Coding challenge explicit ask for a MVVM architecture)
 * This approach encapsulates the entire UI state in a single immutable object,
 * ensuring that the UI always reflects a consistent and predictable state.
 *
 * Pros:
 * - Prevents inconsistent UI states.
 * - Makes state management easier to reason about.
 * - Improves testability and reduces UI-related bugs.
 *
 * Cons:
 * - Requires updating the entire state object for any change,
 *   which may introduce minor overhead compared to mutable properties.
 *
 *   Also this implementation avoid multiple params in TaskListScreenContent
 *   for each state change because all is inside TaskListUiState
 *   @Composable
 * fun TaskListScreenContent(
 *     modifier: Modifier = Modifier,
 *     taskListUiState: TaskListUiState,
 *     onAddTask: () -> Unit,
 * )
 */
class TaskListViewModelMVI(private val getTaskListUseCase: GetTaskListUseCase) : ViewModel() {

    var taskListUiState: TaskListUiState by mutableStateOf(TaskListUiState())
        private set

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            taskListUiState = taskListUiState.copy(isLoading = true, error = null)

            val result = getTaskListUseCase.getTaskList()
            when (result) {
                is ResultState.Success -> taskListUiState = TaskListUiState(tasks = result.data)
                is ResultState.Failure -> taskListUiState =
                    taskListUiState.copy(
                        isLoading = false,
                        error = GeneralError.SOMETHING_WENT_WRONG
                    )
            }
        }
    }
}

data class TaskListUiState(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val tasks: List<Task> = emptyList(),
)