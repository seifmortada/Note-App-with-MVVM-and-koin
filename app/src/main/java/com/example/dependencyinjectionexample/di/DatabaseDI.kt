package com.example.dependencyinjectionexample.di

import android.content.Context
import androidx.room.Room
import com.example.dependencyinjectionexample.database.NoteDatabase
import com.example.dependencyinjectionexample.utils.Constants.NOTE_DATABASE

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db: NoteDatabase) = db.dao()