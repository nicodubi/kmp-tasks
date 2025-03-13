package io.dubiansky.kmptasks

import android.app.Application
import io.dubiansky.kmptasks.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */
class TasksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TasksApplication)
        }
    }
}