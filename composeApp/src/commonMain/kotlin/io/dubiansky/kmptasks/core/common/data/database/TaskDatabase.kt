package io.dubiansky.kmptasks.core.common.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.dubiansky.kmptasks.core.common.data.model.TaskEntity

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@Database(entities = [TaskEntity::class], version = 1)
@ConstructedBy(TaskDatabaseConstructor::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TaskDatabaseConstructor : RoomDatabaseConstructor<TaskDatabase> {
    override fun initialize(): TaskDatabase
}

const val DB_FILE_NAME = "tasks.db"