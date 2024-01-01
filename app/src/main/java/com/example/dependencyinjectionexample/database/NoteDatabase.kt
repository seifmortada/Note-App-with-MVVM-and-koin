package com.example.dependencyinjectionexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dependencyinjectionexample.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun dao(): NoteDao

}