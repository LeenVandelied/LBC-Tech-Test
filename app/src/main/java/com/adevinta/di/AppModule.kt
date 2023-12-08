package com.adevinta.di

import com.adevinta.core.di.coreModule
import org.koin.dsl.module

val appModules = module {
    includes(
        coreModule,
        dataModules
    )
}
    
