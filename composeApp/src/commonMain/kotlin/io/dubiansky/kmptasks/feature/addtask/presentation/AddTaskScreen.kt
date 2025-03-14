package io.dubiansky.kmptasks.feature.addtask.presentation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.dubiansky.kmptasks.core.common.presentation.theme.TaskTopAppBarColorsTheme
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.add_task_description_label
import kmptasks.composeapp.generated.resources.add_task_title_label
import kmptasks.composeapp.generated.resources.back
import kmptasks.composeapp.generated.resources.new_task
import kmptasks.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Nicolas Dubiansky on 13/03/2025.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(onBack: () -> Unit) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val isSaveEnabled = title.isNotBlank()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

                )

            TaskInputField(
                value = description,
                onValueChange = { description = it },
                label = stringResource(Res.string.add_task_description_label),
            )
            Button(
                onClick = {
                    //TODO logic to add task -> viewmodel
                },
                enabled = isSaveEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.save))
            }
        }
    }
}

@Composable
fun TaskInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth()
    )
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
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        colors = colors
    )
}


