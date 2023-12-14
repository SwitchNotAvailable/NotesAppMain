package com.griffith.notesapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.*
import com.griffith.notesapp.model.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes WHERE notes.id=:id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM Notes ORDER BY dateUpdated DESC")
    fun getNotes() : LiveData<List<Note>>

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Insert
    fun insertNote(note: Note)
}