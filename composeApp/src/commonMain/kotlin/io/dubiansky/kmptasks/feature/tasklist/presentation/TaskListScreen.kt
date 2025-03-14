package io.dubiansky.kmptasks.feature.tasklist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.presentation.theme.Dimens
import io.dubiansky.kmptasks.core.common.presentation.theme.TaskTopAppBarColorsTheme
import io.dubiansky.kmptasks.core.common.presentation.ui.LoadingContent
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.add
import kmptasks.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */

/**
 * This composable is StateFul: is in charge to get the state from the ViewModel and pass it to the UI.
 * @param taskListViewModel ViewModel that handles the business logic of this screen
 */
@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel = koinViewModel(), onAddTask: () -> Unit) {
    val uiState by taskListViewModel.taskListUiState
    TaskListScreenContent(taskListUiState = uiState, onAddTask = onAddTask)
}

/**
 * This composable is StateLess: is in charge to display the UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreenContent(
    modifier: Modifier = Modifier,
    taskListUiState: TaskListUiState,
    onAddTask: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TaskListTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddTask() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add)
                )
            }
        }) { paddingValues ->
        Surface(modifier = modifier.padding(paddingValues)) {
            TaskList(taskList = taskListUiState.tasks)
            taskListUiState.error?.let { ShowTaskListError(it) }
            LoadingContent(taskListUiState.isLoading)
        }
    }
}

@Composable
fun ShowTaskListError(error: Exception) {
    //TODO handle errors here
}

@Composable
fun TaskList(modifier: Modifier = Modifier, taskList: List<Task>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.taskItemPadding)
    ) {
        items(items = taskList, key = { it.id }) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onTaskChecked: (Boolean) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.taskItemPadding),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = Dimens.taskItemElevation),
        shape = RoundedCornerShape(Dimens.taskItemCornerRadius),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.taskItemContentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onTaskChecked(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.width(Dimens.taskItemSpacing))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .alpha(if (task.isCompleted) 0.6f else 1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (task.description.isNotEmpty()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) //This is for TopAppBar
@Composable
private fun TaskListTopAppBar(
    colors: TopAppBarColors = TaskTopAppBarColorsTheme(),
) {
    TopAppBar(
        title = {
            Text(stringResource(Res.string.app_name))
        },
        colors = colors
    )
}
