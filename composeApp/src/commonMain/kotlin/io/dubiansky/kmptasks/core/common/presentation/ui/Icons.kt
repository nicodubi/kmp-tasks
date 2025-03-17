package io.dubiansky.kmptasks.core.common.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

/**
* Created by Nicolas Dubiansky on 16/03/2025.
*/


@Composable
fun TaskIconBack() {
    Icon(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        contentDescription = stringResource(Res.string.back)
    )
}
