package com.example.dependencyinjectionexample

import android.app.Application
import com.example.dependencyinjectionexample.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(databaseModule)
        }
    }
}