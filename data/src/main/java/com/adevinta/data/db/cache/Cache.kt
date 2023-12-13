package com.adevinta.data.db.cache

interface Cache {
    fun <T> read(key: String): T?
    fun <T> save(key: String, data: T?)
}

inline fun <reified T> Cache.safeRead(key: String): T? {
    val value = this.read<Any?>(key)
    return if (value is T) value else null
}
