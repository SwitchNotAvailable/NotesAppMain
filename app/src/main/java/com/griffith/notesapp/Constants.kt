package com.griffith.notesapp

import com.griffith.notesapp.model.Note

object Constants {
    const val NAVIGATION_NOTES_LIST = "notesList"
    const val NAVIGATION_NOTES_CREATE = "notesCreated"
    const val NAVIGATION_NOTE_DETAIL = "noteDetail/{noteId}"
    const val NAVIGATION_NOTE_EDIT= "noteEdit/{noteId}"
    const val NAVIGATION_NOTE_ID_Argument = "noteId"
    const val TABLE_NAME = "notes"
    const val DATABASE_NAME = "notesDatabase"

    val noteDetailPlaceHolder = Note(
        note = "Note details not found",
        id = 0,
        title = "Note details not found",
    )


    fun noteDetailNavigation(noteId : Int) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId: Int) = "noteEdit/$noteId"

    fun List<Note>?.orPlaceHolderList(): List<Note> {
        fun placeHolderList(): List<Note> {
            return listOf(Note(id = 0, title = "No Notes Found", note = "Please create a note.", dateUpdated = ""))
        }
        return if (this != null && this.isNotEmpty()){
            this
        } else placeHolderList()
    }

}