package com.adevinta.di

import com.adevinta.data.remote.services.AlbumApiService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
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
}

val servicesModules = module {
    single<AlbumApiService> { get<Retrofit>(named("OK_HTTP_CLIENT")).create(AlbumApiService::class.java) }
}

val dataStoresModules = module {
    //single<AlbumDataStore> { AlbumDataStoreImpl(get()) }
    //single<AlbumLocalStore> { AlbumLocalStoreImpl(get(), get()) }
}

val paperModule = module {
    //single<LocalDb> { LocalDbImpl() }
    //single<Cache> { CacheImpl() }
}


val dataModules = module {
    includes(retrofitModule, servicesModules, dataStoresModules, paperModule)
}

private const val TIMEOUT_SECONDS = 120L
private const val TIMEOUT_TOTAL_SECONDS = 120L