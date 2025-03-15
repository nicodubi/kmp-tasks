package io.dubiansky.kmptasks.core.common.data.model

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TASKS")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)