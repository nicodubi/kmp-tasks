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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

/**
 * Created by Nicolas Dubiansky on 12/03/2025.
 */

/**
 * This composable is StateFul: is in charge to get the state from the ViewModel and pass it to the UI.
 * @param taskListViewModel ViewModel that handles the business logic of this screen
 */
@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel = koinViewModel()) {
    val uiState by taskListViewModel.taskListUiState
    TaskListScreenContent(taskListUiState = uiState)
}

/**
 * This composable is StateLess: is in charge to display the UI.
 */
@Composable
fun TaskListScreenContent(modifier: Modifier = Modifier, taskListUiState: TaskListUiState) {
    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Surface(modifier = modifier.padding(paddingValues)) {
            TaskList(taskList = taskListUiState.tasks)
        }
    }
}

@Composable
fun TaskList(modifier: Modifier = Modifier, taskList: List<Task>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.paddingTaskItem)
    ) {
        items(taskList.size) { task ->
            TaskItem(task = taskList[task])
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
            .padding(Dimens.paddingTaskItem),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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

            Spacer(modifier = Modifier.width(12.dp))

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

