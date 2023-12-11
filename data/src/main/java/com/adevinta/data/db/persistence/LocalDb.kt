package com.adevinta.data.db.persistence

import android.content.Context

interface LocalDb {
    fun init(context: Context)
    fun destroyDb()
    suspend fun <T> read(key: String): T?
    suspend fun <T> save(key: String, data: T?)
}