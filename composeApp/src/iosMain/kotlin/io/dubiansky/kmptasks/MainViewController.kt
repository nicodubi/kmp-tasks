package io.dubiansky.kmptasks

import androidx.compose.ui.window.ComposeUIViewController
import io.dubiansky.kmptasks.core.di.initKoin
import io.dubiansky.kmptasks.core.entrypoint.TasksAppEntryPoint

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    TasksAppEntryPoint()
}