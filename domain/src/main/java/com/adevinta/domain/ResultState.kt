package com.adevinta.domain

sealed class ResultState<out T> {

    // Represents an uninitialized state where no operation has started yet.
    data object Uninitialized : ResultState<Nothing>()

    // Represents a loading state, optionally holding some data (usually from a previous state).
    data class Loading<out T>(val data: T? = null) : ResultState<T>()

    // Represents a failure state with an exception detailing the cause of the failure.
    data class Failure(val exception: Throwable) : ResultState<Nothing>()

    // Represents a success state holding the resulting data.
    data class Success<out T>(val data: T) : ResultState<T>()

    // Utility function to extract the error (Throwable) if the state is Failure, null otherwise.
    fun errorOrNull(): Throwable? = (this as? Failure)?.exception

    // Utility function to extract the data if the state is Success, null otherwise.
    fun getOrNull(): T? = (this as? Success)?.data

    // Property to check if the operation is finished, either successfully or with a failure.
    val isFinished: Boolean
        get() = this is Failure || this is Success

    // Property to check if the operation is still pending (either uninitialized or loading).
    val isPending: Boolean
        get() = this is Uninitialized || this is Loading
}
