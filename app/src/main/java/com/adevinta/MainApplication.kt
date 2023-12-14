package com.adevinta

import android.app.Application
import com.adevinta.data.db.persistence.LocalDb
import com.adevinta.di.appModules
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    private val localDb: LocalDb by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        localDb.init(this)
    }

    private fun initKoin() {
        // start Koin!
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}