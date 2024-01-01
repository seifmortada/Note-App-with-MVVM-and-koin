package com.example.dependencyinjectionexample.di

import com.example.dependencyinjectionexample.repository.DatabaseRepository
import com.example.dependencyinjectionexample.viewmodel.DatabaseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
    factory { DatabaseRepository(get()) }
    viewModel() { DatabaseViewModel(get()) }
}