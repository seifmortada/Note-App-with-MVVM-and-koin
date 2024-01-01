package com.example.dependencyinjectionexample.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dependencyinjectionexample.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun saveNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAllNotes(): Flow<MutableList<NoteEntity>>
}
