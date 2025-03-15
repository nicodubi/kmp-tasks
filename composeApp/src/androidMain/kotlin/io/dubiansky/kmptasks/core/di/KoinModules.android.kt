package io.dubiansky.kmptasks.core.di

import io.dubiansky.kmptasks.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule: Module = module{
    single { getDatabaseBuilder(get()) } //get() is androidContext(this@TasksApplication) in TaskApplication

}
