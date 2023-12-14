package com.griffith.notesapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.griffith.notesapp.model.Note

@Database(version = 1, entities = [Note::class])
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao
}