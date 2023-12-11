package com.adevinta.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<out T>(private val dispatcherIO: CoroutineDispatcher) {
    suspend fun invoke(): T = withContext(dispatcherIO) {
        return@withContext try {
            execute()
        } catch (e: Throwable) {
            throw e
        }
    }

    abstract suspend fun execute(): T
}

abstract class UseCaseWithParams<in P, out R>(private val dispatcherIO: CoroutineDispatcher) {
    suspend fun invoke(params: P): R = withContext(dispatcherIO) {
        return@withContext try {
            execute(params = params)
        } catch (e: Throwable) {
            throw e
        }
    }

    abstract suspend fun execute(params: P): R
}