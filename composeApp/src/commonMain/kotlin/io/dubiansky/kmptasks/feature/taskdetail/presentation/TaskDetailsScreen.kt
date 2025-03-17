package io.dubiansky.kmptasks.feature.taskdetail.presentation

/**
 * Created by Nicolas Dubiansky on 16/03/2025.
 */

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.koin.compose.viewmodel.koinViewModel
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.Error
import io.dubiansky.kmptasks.core.common.presentation.theme.Dimens
import io.dubiansky.kmptasks.core.common.presentation.theme.TaskTopAppBarColorsTheme
import io.dubiansky.kmptasks.core.common.presentation.ui.ErrorContent
import io.dubiansky.kmptasks.core.common.presentation.ui.LoadingContent
import io.dubiansky.kmptasks.core.common.presentation.ui.TaskIconBack
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.completed
import kmptasks.composeapp.generated.resources.pending
import kmptasks.composeapp.generated.resources.status
import kmptasks.composeapp.generated.resources.task_details
import org.jetbrains.compose.resources.stringResource

@Composable
fun TaskDetailsScreen(
    viewModel: TaskDetailsViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val task = viewModel.task
    TaskDetailsScreenContent(
        isLoading = viewModel.isLoading,
        task = task,
        onBack = onBack,
        onChangeCompletedTask = { task?.let { viewModel.onChangeCompletedTask(it) } }
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
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TaskDetailsTopAppBar(onBack) }
    ) { paddingValues ->
        Surface(modifier = modifier.padding(paddingValues)) {
            task?.let {
                TaskDetails(
                    task = it, onChangeCompletedTask = onChangeCompletedTask
                )
            }

            LoadingContent(isLoading)
        }
    }
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
    colors: TopAppBarColors = TaskTopAppBarColorsTheme(),
) {
    TopAppBar(
        title = { Text(stringResource(Res.string.task_details)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                TaskIconBack()
            }
        },
        colors = colors
    )
}
