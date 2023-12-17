package com.adevinta.data

import com.adevinta.core.models.HttpCodeException
import com.adevinta.core.models.NoNetworkException
import com.adevinta.core.models.ParsingJsonException
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException

internal suspend fun <T> runSafeHttpCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: HttpException) {
        Result.failure(HttpCodeException(code = e.code(), message = e.message.orEmpty()))
    } catch (e: IOException) {
        Result.failure(NoNetworkException(e.message.orEmpty()))
    } catch (e: JsonSyntaxException) {
        Result.failure(ParsingJsonException(e.message.orEmpty()))
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
