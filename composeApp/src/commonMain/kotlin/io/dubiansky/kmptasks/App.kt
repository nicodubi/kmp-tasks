package io.dubiansky.kmptasks


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.dubiansky.kmptasks.ui.theme.TasksAppTheme
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    TasksAppTheme {
        Scaffold { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)){
                Text(style = MaterialTheme.typography.headlineLarge, text = stringResource(Res.string.app_name))
            }
        }
    }
}