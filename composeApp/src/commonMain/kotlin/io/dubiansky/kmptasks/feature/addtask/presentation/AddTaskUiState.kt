package io.dubiansky.kmptasks.feature.addtask.presentation

import io.dubiansky.kmptasks.core.common.data.model.Task

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */
data class AddTaskUiState(
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val isTaskAddedSuccessfully: Boolean = false,
)