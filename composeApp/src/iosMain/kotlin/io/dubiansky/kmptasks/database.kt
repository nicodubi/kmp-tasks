package io.dubiansky.kmptasks

import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.dubiansky.kmptasks.core.common.data.database.TaskDatabase
import platform.Foundation.NSHomeDirectory
import io.dubiansky.kmptasks.core.common.data.database.DB_FILE_NAME

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */

fun getDatabaseBuilder(): RoomDatabase.Builder<TaskDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_FILE_NAME"
    return Room.databaseBuilder<TaskDatabase>(
        name = dbFilePath,
    ).setDriver(BundledSQLiteDriver())

}