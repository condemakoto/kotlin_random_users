package com.conde.kun.fija

import android.app.Application
import android.util.Log
import com.amitshekhar.DebugDB
import com.conde.kun.fija.di.dataModule
import com.conde.kun.fija.di.domainModule
import com.conde.kun.fija.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FijaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FijaApplication)
            modules(listOf(viewModelModule, domainModule, dataModule))
        }
        Log.i("lala", DebugDB.getAddressLog())
    }
}