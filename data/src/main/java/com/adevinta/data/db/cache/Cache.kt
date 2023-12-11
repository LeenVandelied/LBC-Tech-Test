package com.adevinta.data.db.cache

interface Cache {
    fun <T> read(key: String): T?
    fun <T> save(key: String, data: T?)
}