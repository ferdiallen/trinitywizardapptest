package com.ferdialif.testapplication.presentation.main

import android.icu.text.SimpleDateFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ferdialif.testapplication.R
import com.ferdialif.testapplication.data.local.ContactsEntity
import com.ferdialif.testapplication.ui.theme.DefaultColor
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToDetailScreen: (Int) -> Unit,
    viewModel: MainViewModel
) {
    val contactData by viewModel.contactData.collectAsState()
    var isSearchActive by remember {
        mutableStateOf(false)
    }
    var shouldShowInsert by remember {
        mutableStateOf(false)
    }
    var searchText by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.serializedData(context, R.raw.data)
    }
    val searchResult = remember(searchText) {
        contactData.let {
            it.filter { result ->
                "${result.firstName} ${result.lastName}".lowercase()
                    .contains(searchText.lowercase())
            }
        }
    }
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RectangleShape
        ) {
            AnimatedVisibility(
                visible = !isSearchActive,
                enter = fadeIn(tween(400)),
                exit = slideOutVertically(tween(200))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        isSearchActive = !isSearchActive
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = stringResource(R.string.contacts_title),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    IconButton(onClick = {
                        shouldShowInsert = !shouldShowInsert
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isSearchActive,
                enter = slideInVertically(tween(400), initialOffsetY = {
                    1000
                }),
                exit = slideOutVertically(tween(500), targetOffsetY = {
                    it
                })
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            isSearchActive = false
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null
                            )
                        }
                    },
                    shape = CircleShape
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (!isSearchActive) {
                items(contactData) {
                    ContactItem(modifier = Modifier.size(200.dp), name = {
                        "${it.firstName} ${it.lastName}"
                    }, onClick = {
                        navigateToDetailScreen(it.id ?: return@ContactItem)
                    })
                }
            } else {
                items(searchResult) {
                    println("Search result : ${it.firstName}")
                    ContactItem(modifier = Modifier.size(200.dp), name = {
                        "${it.firstName} ${it.lastName}"
                    }, onClick = {
                        navigateToDetailScreen(it.id ?: return@ContactItem)
                    })
                }

            }
        }
    }
    if (shouldShowInsert) {
        Dialog(onDismissRequest = {
            shouldShowInsert = false
        }) {
            InsertNewItem(onSaved = {
                shouldShowInsert = false
                viewModel.saveNewContact(it)
            })

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactItem(
    modifier: Modifier = Modifier,
    name: () -> String,
    onClick: () -> Unit
) {
    Card(onClick = onClick::invoke, modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(70.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(DefaultColor)
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = name(), fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InsertNewItem(
    onSaved: (ContactsEntity) -> Unit
) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var birthData by remember {
        mutableStateOf("")
    }
    var shouldShowDatePicker by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.insert_new_data_title),
                fontSize = 20.sp, fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.wrapContentSize()) {
                Text(text = "First name", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = firstName, onValueChange = {
                    firstName = it
                })
                Text(text = "Last name", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = lastName, onValueChange = {
                    lastName = it
                })
                Text(text = "Email", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = email, onValueChange = {
                    email = it
                })
                Text(text = "Birth Date", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = birthData, onValueChange = {
                    birthData = it
                }, trailingIcon = {
                    IconButton(onClick = { shouldShowDatePicker = !shouldShowDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                onSaved(ContactsEntity(
                    firstName = firstName,
                    lastName = lastName,
                    dateBirth = birthData,
                    email = email
                ))
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Save")
            }
        }
    }

    val pickerState = rememberDatePickerState()
    LaunchedEffect(key1 = pickerState.selectedDateMillis, block = {
        birthData = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(pickerState.selectedDateMillis ?: 0L)
    })
    if (shouldShowDatePicker) {
        DatePickerDialog(onDismissRequest = {
            shouldShowDatePicker = false
        }, confirmButton = {
        }) {
            DatePicker(state = pickerState, showModeToggle = false)
        }
    }
}