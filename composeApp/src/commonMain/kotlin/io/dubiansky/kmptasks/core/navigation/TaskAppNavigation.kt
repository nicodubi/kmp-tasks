package io.dubiansky.kmptasks.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.dubiansky.kmptasks.feature.addtask.presentation.AddTaskScreen
import io.dubiansky.kmptasks.feature.tasklist.presentation.TaskListScreen

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */

@kotlinx.serialization.Serializable
object TaskListRoute

@kotlinx.serialization.Serializable
object AddTaskRoute

@Composable
fun TasksAppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TaskListRoute::class) {
        composable<TaskListRoute> {
            TaskListScreen(onAddTask = { navController.navigate(AddTaskRoute) })
        }

        composable<AddTaskRoute> {
            AddTaskScreen(onBack = { navController.popBackStack() })
        }
    }
}