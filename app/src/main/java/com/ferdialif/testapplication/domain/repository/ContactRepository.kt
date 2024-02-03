package com.ferdialif.testapplication.domain.repository

import com.ferdialif.testapplication.data.database.ContactsDatabase
import com.ferdialif.testapplication.data.local.ContactsEntity
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val database: ContactsDatabase
) {
    suspend fun insertData(data: ContactsEntity) = database.dao().insertData(data)
    fun readData() = database.dao().getAllDataFromContacts()

    fun getDataFromContacts(id:Int) = database.dao().getDataFromContacts(id)

    suspend fun updateData(data:ContactsEntity) = database.dao().updateCurrentData(data)
}