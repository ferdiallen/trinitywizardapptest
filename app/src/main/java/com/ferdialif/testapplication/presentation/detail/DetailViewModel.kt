package com.ferdialif.testapplication.presentation.detail

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdialif.testapplication.data.local.ContactsEntity
import com.ferdialif.testapplication.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {
    private val _contactData = MutableStateFlow<ContactsEntity?>(null)
    val contactData = _contactData.asStateFlow()

    fun readData(data: Int) = viewModelScope.launch(Dispatchers.IO) {
        _contactData.update {
            repository.getDataFromContacts(data)
        }
    }

     fun updateCurrentData(data: ContactsEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateData(data)
    }
}