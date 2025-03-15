package io.dubiansky.kmptasks

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Room
import io.dubiansky.kmptasks.core.common.data.database.DB_FILE_NAME
import io.dubiansky.kmptasks.core.common.data.database.TaskDatabase

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<TaskDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DB_FILE_NAME)
    return Room.databaseBuilder<TaskDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}