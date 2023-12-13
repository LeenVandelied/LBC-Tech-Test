package com.adevinta.data.db.persistence

import android.content.Context
import io.paperdb.Book
import io.paperdb.Paper

internal class LocalDbImpl : LocalDb {
    private lateinit var book: Book

    override fun init(context: Context) {
        Paper.init(context)
        book = Paper.book()
    }

    override fun destroyDb() = book.destroy()

    override suspend fun <T> read(key: String): T? {
        return try {
            book.read<T>(key)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun <T> save(key: String, data: T?) {
        if (data == null) {
            book.delete(key)
        } else {
            book.write(key, data)
        }
    }
}