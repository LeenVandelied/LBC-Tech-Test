package com.adevinta.data.db.cache

interface Cache {
    fun <T> read(key: String): T?
    fun <T> save(key: String, data: T?)
}

// this extension manage the cast "  as? T " who can provide errors
inline fun <reified T> Cache.safeRead(key: String): T? {
    val value = this.read<Any?>(key)
    return if (value is T) value else null
}
