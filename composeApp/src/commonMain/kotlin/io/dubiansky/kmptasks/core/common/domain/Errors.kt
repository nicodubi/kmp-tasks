package io.dubiansky.kmptasks.core.common.domain

import kmptasks.composeapp.generated.resources.Res
import kmptasks.composeapp.generated.resources.something_went_wrong
import kmptasks.composeapp.generated.resources.title_cannot_be_empty
import kmptasks.composeapp.generated.resources.title_too_long
import org.jetbrains.compose.resources.StringResource

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */

enum class TaskCreationError(override val stringRes: StringResource) : Error {
    TITLE_IS_BLANK(Res.string.title_cannot_be_empty),
    TITLE_IS_TOO_LONG(Res.string.title_too_long)
}

enum class GeneralError(override val stringRes: StringResource) : Error {
    SOMETHING_WENT_WRONG(Res.string.something_went_wrong)
}


