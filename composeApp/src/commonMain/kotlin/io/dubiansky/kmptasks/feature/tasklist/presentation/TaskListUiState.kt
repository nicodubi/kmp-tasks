package io.dubiansky.kmptasks.feature.tasklist.presentation

import io.dubiansky.kmptasks.core.common.data.model.Task

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
data class TaskListUiState(
    val isLoading : Boolean = false,
    val error : Exception? = null,
    val tasks : List<Task> = emptyList()
)