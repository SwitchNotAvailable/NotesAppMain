package com.griffith.notesapp

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.room.Room
import com.griffith.notesapp.persistence.NotesDao
import com.griffith.notesapp.persistence.NotesDatabase

class NotesApp : Application() {
    private var db : NotesDatabase? = null

    init {
        instance = this
    }

    private fun getDb() : NotesDatabase {
        if (db != null) {
            return db!!
        } else {
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                NotesDatabase::class.java, Constants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

            return db!!
        }
    }

    companion object {
        private var instance: NotesApp? = null

        fun getDao() : NotesDao {
            return instance!!.getDb().NotesDao()
        }

        fun getUriPermission(uri: Uri) {
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}