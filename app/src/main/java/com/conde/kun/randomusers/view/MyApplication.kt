package com.conde.kun.randomusers.view

import android.app.Application
import com.conde.kun.randomusers.di.module.dataModule
import com.conde.kun.randomusers.di.module.domainModule
import com.conde.kun.randomusers.di.module.usersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(usersModule, domainModule, dataModule))
        }
    }
}