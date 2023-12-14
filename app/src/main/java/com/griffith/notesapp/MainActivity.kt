package com.griffith.notesapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.griffith.notesapp.ui.NoteCreate.CreateNoteScreen
import com.griffith.notesapp.ui.NoteDetail.NoteDetailPage
import com.griffith.notesapp.ui.NoteEdit.NoteEditScreen
import com.griffith.notesapp.ui.NoteViewModelFactory
import com.griffith.notesapp.ui.NotesList.NotesList
import com.griffith.notesapp.ui.NotesViewModel


class MainActivity : ComponentActivity() {
    private lateinit var notesViewModel : NotesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retrieve viewModel
        notesViewModel =  NoteViewModelFactory(NotesApp.getDao()).create(NotesViewModel::class.java)


        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Constants.NAVIGATION_NOTES_LIST
            ) {
                // Notes List
                composable(Constants.NAVIGATION_NOTES_LIST) { NotesList(navController, notesViewModel) }

                // Notes Detail page
                composable(
                    Constants.NAVIGATION_NOTE_DETAIL,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteDetailPage(noteId = it, navController, notesViewModel) }
                }

                // Notes Edit page
                composable(
                    Constants.NAVIGATION_NOTE_EDIT,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteEditScreen(noteId = it, navController, notesViewModel) }
                }

                // Create Note Page
                composable(Constants.NAVIGATION_NOTES_CREATE) { CreateNoteScreen(navController, notesViewModel) }

            }

        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape mode
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait mode
        }
    }
}
