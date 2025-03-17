package io.dubiansky.kmptasks.core.di

import androidx.room.RoomDatabase
import io.dubiansky.kmptasks.core.common.data.database.TaskDao
import io.dubiansky.kmptasks.core.common.data.database.TaskDatabase
import io.dubiansky.kmptasks.core.common.data.repository.TaskRepositoryImpl
import io.dubiansky.kmptasks.core.common.data.source.TaskLocalDataSource
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.addtask.domain.AddTaskUseCase
import io.dubiansky.kmptasks.feature.addtask.presentation.AddTaskViewModel
import io.dubiansky.kmptasks.feature.taskdetail.domain.GetTaskDetailUseCase
import io.dubiansky.kmptasks.feature.taskdetail.presentation.TaskDetailsViewModel
import io.dubiansky.kmptasks.feature.tasklist.domain.ChangeCompletedTaskUseCase
import io.dubiansky.kmptasks.feature.tasklist.domain.GetTaskListUseCase
import io.dubiansky.kmptasks.feature.tasklist.presentation.TaskListViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */

val dataModule = module {
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
    single<TaskDao> {
        val dbBuilder = get<RoomDatabase.Builder<TaskDatabase>>()
        dbBuilder.build().taskDao()
    }
}

val useCaseModule = module {
    factoryOf(::GetTaskListUseCase)
    factoryOf(::AddTaskUseCase)
    factoryOf(::ChangeCompletedTaskUseCase)
    factoryOf(::GetTaskDetailUseCase)

}

val viewModelModule = module {
    viewModelOf(::TaskListViewModel)
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::TaskDetailsViewModel)

}

expect val nativeModule: Module
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, useCaseModule, viewModelModule, nativeModule)
    }
}