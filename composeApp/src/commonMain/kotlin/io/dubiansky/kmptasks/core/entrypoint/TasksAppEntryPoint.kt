package io.dubiansky.kmptasks.core.entrypoint


import androidx.compose.runtime.*
import io.dubiansky.kmptasks.core.navigation.TasksAppNavigation
import io.dubiansky.kmptasks.core.common.presentation.theme.TasksAppTheme

import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun TasksAppEntryPoint() {
    TasksAppTheme {
        TasksAppNavigation()
    }
}