package io.dubiansky.kmptasks.core.common.domain

import org.jetbrains.compose.resources.StringResource

/**
 * Created by Nicolas Dubiansky on 14/03/2025.
 */


sealed interface Error {
    val stringRes: StringResource
}

sealed interface ResultState<out D, out E : Error> {
    data class Success<out D, out E : Error>(val data: D) :
        ResultState<D, E>

    data class Failure<out D, out E : Error>(val errorCause: E) :
        ResultState<D, E>
}

