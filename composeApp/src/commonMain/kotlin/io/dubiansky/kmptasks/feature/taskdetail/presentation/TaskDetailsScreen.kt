package io.dubiansky.kmptasks.feature.taskdetail.presentation

/**
 * Created by Nicolas Dubiansky on 16/03/2025.
 */

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.koin.compose.viewmodel.koinViewModel
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.presentation.theme.Dimens
import io.dubiansky.kmptasks.core.common.presentation.theme.TaskTopAppBarColorsTheme
import io.dubiansky.kmptasks.core.common.presentation.ui.LoadingContent
import io.dubiansky.kmptasks.core.common.presentation.ui.TaskIconBack
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.completed
import kmptasks.composeapp.generated.resources.pending
import kmptasks.composeapp.generated.resources.status
import kmptasks.composeapp.generated.resources.task_details
import org.jetbrains.compose.resources.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kmptasks.composeapp.generated.resources.cancel
import kmptasks.composeapp.generated.resources.delete
import kmptasks.composeapp.generated.resources.delete_confirm_button_dialog
import kmptasks.composeapp.generated.resources.delete_task_description_dialog
import kmptasks.composeapp.generated.resources.delete_task_title_dialog

@Composable
fun TaskDetailsScreen(
    viewModel: TaskDetailsViewModel = koinViewModel(),
    onBack: () -> Unit,
    onDeletedTask: () -> Unit,
) {
    val task = viewModel.task
    val taskDeleted = viewModel.taskDeleted

    LaunchedEffect(taskDeleted) {
        if (taskDeleted) {
            onDeletedTask()
        }
    }

    TaskDetailsScreenContent(
        isLoading = viewModel.isLoading,
        task = task,
        onBack = onBack,
        onChangeCompletedTask = { task?.let { viewModel.onChangeCompletedTask(it) } },
        onDeleteTask = { task?.let { viewModel.onDeleteTask(it) } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    task: Task?,
    onBack: () -> Unit,
    onChangeCompletedTask: () -> Unit,
    onDeleteTask: () -> Unit,
) {
    var showDeleteTaskDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TaskDetailsTopAppBar(
                onBack = onBack,
                onDeleteTask = { task?.let { showDeleteTaskDialog = true } })
        }
    ) { paddingValues ->
        Surface(modifier = modifier.padding(paddingValues)) {
            task?.let {
                TaskDetails(
                    task = it, onChangeCompletedTask = onChangeCompletedTask
                )
            }

            LoadingContent(isLoading)
            if (showDeleteTaskDialog) {
                DeleteTaskDialog(
                    onDismissRequest = { showDeleteTaskDialog = false },
                    onConfirmDeletion = {
                        onDeleteTask()
                        showDeleteTaskDialog = false
                    })
            }
        }
    }
}

@Composable
fun DeleteTaskDialog(onDismissRequest: () -> Unit, onConfirmDeletion: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(stringResource(Res.string.delete_task_title_dialog)) },
        text = { Text(stringResource(Res.string.delete_task_description_dialog)) },
        confirmButton = {
            TextButton(onClick = onConfirmDeletion) {
                Text(stringResource(Res.string.delete_confirm_button_dialog), color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(Res.string.cancel))
            }
        }
    )
}


@Composable
fun TaskDetails(task: Task, onChangeCompletedTask: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Dimens.taskItemContentPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        if (task.description.isNotBlank()) {
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.dividerPadding),
            thickness = Dimens.dividerThickness,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = stringResource(Res.string.status),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (task.isCompleted) stringResource(Res.string.completed) else
                    stringResource(Res.string.pending),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = if (task.isCompleted) Color.Green else Color.Red
            )


            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onChangeCompletedTask() }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailsTopAppBar(
    onBack: () -> Unit,
    onDeleteTask: () -> Unit,
    colors: TopAppBarColors = TaskTopAppBarColorsTheme(),
) {
    TopAppBar(
        title = { Text(stringResource(Res.string.task_details)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                TaskIconBack()
            }
        },
        actions = {
            IconButton(onClick = onDeleteTask) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(Res.string.delete))
            }
        },
        colors = colors
    )
}
