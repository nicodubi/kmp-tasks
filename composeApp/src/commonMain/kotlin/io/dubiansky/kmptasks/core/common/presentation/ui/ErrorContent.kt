package io.dubiansky.kmptasks.core.common.presentation.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.dubiansky.kmptasks.core.common.domain.Error
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.something_went_wrong
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */

@Composable
fun ErrorContent(
    error: Error,
    snackbarHostState: SnackbarHostState,
) {
    val message = stringResource(error.stringRes)
    LaunchedEffect(error) {
        snackbarHostState.showSnackbar(message)
    }
}