package com.adevinta.domain

sealed class ResultState<out T> {
    data object Uninitialized : ResultState<Nothing>()
    data class Loading<out T>(val data: T? = null) : ResultState<T>()
    data class Failure(val exception: Throwable) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()

    fun errorOrNull(): Throwable? = (this as? Failure)?.exception
    fun getOrNull(): T? = (this as? Success)?.data

    val isFinished: Boolean
        get() = this is Failure || this is Success

    val isPending: Boolean
        get() = this is Uninitialized || this is Loading

}