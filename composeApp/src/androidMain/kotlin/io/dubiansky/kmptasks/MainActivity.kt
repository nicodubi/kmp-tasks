package io.dubiansky.kmptasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.dubiansky.kmptasks.core.entrypoint.TasksAppEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasksAppEntryPoint()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    TasksAppEntryPoint()
}