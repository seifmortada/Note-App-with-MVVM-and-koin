package com.example.dependencyinjectionexample.repository

import com.example.dependencyinjectionexample.database.NoteDao
import com.example.dependencyinjectionexample.entity.NoteEntity

class DatabaseRepository(private val dao: NoteDao) {

    suspend fun saveNote(note: NoteEntity) =
        dao.saveNote(note)

    fun getAllNotes() =
        dao.getAllNotes()

}