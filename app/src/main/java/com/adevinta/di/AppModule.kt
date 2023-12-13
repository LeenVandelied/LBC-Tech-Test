package com.adevinta.di

import com.adevinta.core.di.coreModules
import com.adevinta.data.di.dataModules
import com.adevinta.domain.di.domainModules
import com.adevinta.home.di.homeModules
import org.koin.dsl.module

val appModules = module {
    includes(
        coreModules,
        dataModules,
        homeModules,
        domainModules
    )
}
