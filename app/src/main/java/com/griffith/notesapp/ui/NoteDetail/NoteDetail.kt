package com.griffith.notesapp.ui.NoteDetail

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.griffith.notesapp.Constants
import com.griffith.notesapp.Constants.noteDetailPlaceHolder
import com.griffith.notesapp.ui.GenericAppBar

import com.griffith.notesapp.ui.theme.NotesTheme
import com.griffith.notesapp.ui.NotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailPage(noteId: Int, navController: NavController, viewModel: NotesViewModel) {
    val scope = rememberCoroutineScope()

    val note = remember {
        mutableStateOf(noteDetailPlaceHolder)
    }

    LaunchedEffect(true){
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: noteDetailPlaceHolder
        }
    }

    NotesTheme {
        Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold(
                topBar = { GenericAppBar(
                    title = note.value.title,
                    onIconClick = {
                                  navController.navigate(Constants.noteEditNavigation(note.value.id ?: 0))
                    },
                    icon = {
                           Icon(
                               imageVector = ImageVector.vectorResource(id = com.griffith.notesapp.R.drawable.editnote),
                               contentDescription = stringResource(id = com.griffith.notesapp.R.string.edit_note),
                               tint = Color.Black
                           )
                    },
                    iconState = remember {
                        mutableStateOf(true)
                    }
                )}
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    if (note.value.imageUri != null && note.value.imageUri!!.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(note.value.imageUri))
                                    .build()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(0.3f)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = note.value.title,
                        modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = note.value.dateUpdated,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = note.value.note,
                        modifier = Modifier.padding(12.dp),
                    )
                }

            }
        }
    }
}