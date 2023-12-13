package com.adevinta.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single(named(IoDispatcher)) { Dispatchers.IO }
    single(named(MainDispatcher)) { Dispatchers.Default }
}

val coreModules = module {
    includes(
        dispatchersModule
    )
}

const val IoDispatcher = "io_dispatcher"
const val MainDispatcher = "main_dispatcher"
