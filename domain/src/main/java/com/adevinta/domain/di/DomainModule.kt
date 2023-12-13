package com.adevinta.domain.di

import com.adevinta.domain.repositories.AlbumRepository
import com.adevinta.domain.repositories.AlbumRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    single<AlbumRepository> { AlbumRepositoryImpl(get(), get()) }
}

val domainModules = module { includes(repositoriesModule) }
