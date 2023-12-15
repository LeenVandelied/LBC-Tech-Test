package com.adevinta.data.di

import androidx.room.Room.databaseBuilder
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumDataStoreImpl
import com.adevinta.data.album.AlbumLocalStore
import com.adevinta.data.album.AlbumLocalStoreImpl
import com.adevinta.data.db.cache.Cache
import com.adevinta.data.db.cache.CacheImpl
import com.adevinta.data.db.persistence.LocalDatabase
import com.adevinta.data.remote.services.AlbumApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single(named("OK_HTTP_CLIENT")) {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT_TOTAL_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit>(named("ALBUM_API")) {
        Retrofit.Builder()
            .client(get(named("OK_HTTP_CLIENT")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://static.leboncoin.fr/")
            .build()
    }
}

val servicesModules = module {
    single<AlbumApiService> {
        get<Retrofit>(named("ALBUM_API")).create(AlbumApiService::class.java)
    }
}

val dataStoresModules = module {
    single {
        databaseBuilder(
            get(),
            LocalDatabase::class.java,
            "albums"
        ).build()
    }
    single<AlbumDataStore> { AlbumDataStoreImpl(get()) }
    single<AlbumLocalStore> { AlbumLocalStoreImpl(get() ,get()) }
}

val dbModule = module {
    single {
        val database = get<LocalDatabase>()
        database.localDbDao()
    }
    single<Cache> { CacheImpl() }
}

val dataModules = module {
    includes(retrofitModule, servicesModules, dataStoresModules, dbModule)
}

private const val TIMEOUT_SECONDS = 120L
private const val TIMEOUT_TOTAL_SECONDS = 120L