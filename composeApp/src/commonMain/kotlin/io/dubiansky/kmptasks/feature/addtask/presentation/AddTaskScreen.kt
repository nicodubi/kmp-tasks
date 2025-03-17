package io.dubiansky.kmptasks.feature.addtask.presentation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dubiansky.kmptasks.core.common.data.model.Task
import io.dubiansky.kmptasks.core.common.domain.Error
import io.dubiansky.kmptasks.core.common.domain.TaskCreationError
import io.dubiansky.kmptasks.core.common.presentation.theme.TaskTopAppBarColorsTheme
import io.dubiansky.kmptasks.core.common.presentation.ui.ErrorContent
import io.dubiansky.kmptasks.core.common.presentation.ui.LoadingContent
import io.dubiansky.kmptasks.core.common.presentation.ui.TaskIconBack
import io.dubiansky.kmptasks.feature.addtask.data.TaskInput
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.add_task_description_label
import kmptasks.composeapp.generated.resources.add_task_title_label
import kmptasks.composeapp.generated.resources.back
import kmptasks.composeapp.generated.resources.new_task
import kmptasks.composeapp.generated.resources.save
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */


/**
 * This composable is StateFul: is in charge to get the state from the ViewModel and pass it to the UI.
 * @param addTaskViewModel ViewModel that handles the business logic of this screen
 * @param onBack callback event to navigate back
 */
@Composable
fun AddTaskScreen(
    addTaskViewModel: AddTaskViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val error = addTaskViewModel.error
    val showErrorOn = error?.let { getShowErrorOnFrom(it) }

    AddTaskScreenContent(
        isLoading = addTaskViewModel.isLoading,
        error = error,
        showErrorOn = showErrorOn,
        taskAdded = addTaskViewModel.taskAdded,
        onBack = onBack,
        onAddTask = {
            addTaskViewModel.addTask(it)
        }
    )
}

private fun getShowErrorOnFrom(error: Error): ShowErrorOn = when (error) {
    TaskCreationError.TITLE_IS_TOO_LONG, TaskCreationError.TITLE_IS_BLANK -> ShowErrorOn.TITLE
    else -> ShowErrorOn.GENERAL

}

enum class ShowErrorOn {
    TITLE, DESCRIPTION, GENERAL
}

/**
 * This composable is StateLess: is in charge to display the UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    error: Error?,
    showErrorOn: ShowErrorOn?,
    taskAdded: Task?,
    onAddTask: (TaskInput) -> Unit,
    onBack: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { AddTaskTopAppBar(onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TaskInputField(
                value = title,
                onValueChange = { title = it },
                label = stringResource(Res.string.add_task_title_label),
                singleLine = true,
                errorMessage = error?.stringRes.takeIf { showErrorOn == ShowErrorOn.TITLE }
            )

            TaskInputField(
                value = description,
                onValueChange = { description = it },
                singleLine = false,
                modifier = Modifier.fillMaxHeight(0.9f),
                label = stringResource(Res.string.add_task_description_label),
            )
            Button(
                onClick = {
                    onAddTask(TaskInput(title, description))
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Text(stringResource(Res.string.save))
            }
        }

        if (showErrorOn == ShowErrorOn.GENERAL && error != null) {
            ErrorContent(error, snackbarHostState)
        }

        LoadingContent(isLoading)
        val taskAddedSuccess = taskAdded != null
        if (taskAddedSuccess) onBack()

    }
}


@Composable
fun TaskInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
    errorMessage: StringResource? = null,
) {

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = singleLine,
            modifier = modifier.fillMaxWidth(),
            isError = errorMessage != null
        )
        if (errorMessage != null) {
            Text(
                text = stringResource(errorMessage),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class) //This is for TopAppBar
@Composable
private fun AddTaskTopAppBar(
    onBack: () -> Unit,
    colors: TopAppBarColors = TaskTopAppBarColorsTheme(),
) {
    TopAppBar(
        title = {
            Text(stringResource(Res.string.new_task))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                TaskIconBack()
            }
        },
        colors = colors
    )
}


