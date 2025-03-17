package io.dubiansky.kmptasks.core.common.presentation.ui

/**
 * Created by Nicolas Dubiansky on 17/03/2025.
 */

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.vector.ImageVector
import io.dubiansky.kmptasks.core.common.presentation.theme.Dimens

@Composable
fun EmptyState(
    show: Boolean,
    icon: ImageVector,
    title: String,
    description: String,
    buttonText: String,
    onActionClick: () -> Unit,
) {
    if (show) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.emptyStatePadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(Dimens.emptyStateIconSize)
            )

            Spacer(modifier = Modifier.height(Dimens.emptyStateSpacingLarge))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(Dimens.emptyStateSpacingSmall))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(Dimens.emptyStateSpacingLarge))

            ElevatedButton(onClick = onActionClick) {
                Text(buttonText)
            }
        }
    }
}
