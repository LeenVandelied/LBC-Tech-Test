package com.adevinta.data.db.cache

// Managed with safeRead extension
@Suppress("UNCHECKED_CAST")
internal class CacheImpl : Cache {
    private var cache: HashMap<String, Any?> = hashMapOf()

    override fun <T> read(key: String): T? = cache[key] as? T

    override fun <T> save(key: String, data: T?) {
        cache[key] = data
    }
}
