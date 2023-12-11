package com.adevinta.data.db.cache

internal class CacheImpl : Cache {
    var cache: HashMap<String, Any?> = hashMapOf()
    override fun <T> read(key: String): T? = cache[key] as? T

    override fun <T> save(key: String, data: T?) {
        cache[key] = data
    }
}