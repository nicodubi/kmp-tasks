package io.dubiansky.kmptasks.core.di

import io.dubiansky.kmptasks.core.common.data.repository.TaskRepositoryImpl
import io.dubiansky.kmptasks.core.common.data.source.TaskLocalDataSource
import io.dubiansky.kmptasks.core.common.domain.TaskRepository
import io.dubiansky.kmptasks.feature.tasklist.domain.GetTaskListUseCase
import io.dubiansky.kmptasks.feature.tasklist.presentation.TaskListViewModel
import org.koin.core.context.startKoin
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
    singleOf(::TaskLocalDataSource)
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
}

val useCaseModule = module {
    factoryOf(::GetTaskListUseCase)
}

val viewModelModule = module {
    viewModelOf(::TaskListViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, useCaseModule, viewModelModule)
    }
}