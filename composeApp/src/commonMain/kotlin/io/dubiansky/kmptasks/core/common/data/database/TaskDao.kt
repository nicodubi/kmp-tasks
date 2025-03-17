package io.dubiansky.kmptasks.core.common.data.database

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */
import androidx.room.*
import io.dubiansky.kmptasks.core.common.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TASKS ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TaskEntity>> //is not suspend fun because Flow handle the background I/O

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: Long): Int

    @Query("SELECT * FROM TASKS WHERE id = :id") //is not suspend fun because Flow handle the background I/O
    fun getTaskDetails(id: Long): Flow<TaskEntity?>
}