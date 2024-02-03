package com.ferdialif.testapplication.presentation.main

import android.content.Context
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdialif.testapplication.data.local.ContactsEntity
import com.ferdialif.testapplication.data.local.toContactsEntity
import com.ferdialif.testapplication.domain.repository.ContactRepository
import com.ferdialif.testapplication.utils.serializeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {
    private val _contactData = MutableStateFlow<List<ContactsEntity>>(emptyList())
    val contactData = _contactData.asStateFlow()

    init {
        readContactData()
    }

    private fun readContactData() = viewModelScope.launch(Dispatchers.IO) {
        repository.readData().collectLatest { result ->
            _contactData.update {
                result
            }
        }
    }

    fun saveNewContact(data:ContactsEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertData(data)
    }

    fun serializedData(context: Context, @RawRes data: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            if (_contactData.value.isNotEmpty()) {
                return@launch
            }
            serializeData(context, data).forEach { result ->
                repository.insertData(result.toContactsEntity())
            }
        }
}