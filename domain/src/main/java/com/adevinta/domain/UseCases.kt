package com.adevinta.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


// added to manage  the dispatcherIO and the try catch
abstract class UseCase<out T>(private val dispatcherIO: CoroutineDispatcher) {
    suspend fun invoke(): T =
        withContext(dispatcherIO) {
            return@withContext try {
                execute()
            } catch (e: Throwable) {
                throw e
            }
        }

    abstract suspend fun execute(): T
}

// // Not used here in this test but can be use to call UseCase with Params
abstract class UseCaseWithParams<in P, out R>(private val dispatcherIO: CoroutineDispatcher) {
    suspend fun invoke(params: P): R =
        withContext(dispatcherIO) {
            return@withContext try {
                execute(params = params)
            } catch (e: Throwable) {
                throw e
            }
        }

    abstract suspend fun execute(params: P): R
}