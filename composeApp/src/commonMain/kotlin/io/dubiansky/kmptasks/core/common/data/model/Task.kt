package io.dubiansky.kmptasks.core.common.data.model

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */
data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
)