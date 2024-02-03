package com.ferdialif.testapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ferdialif.testapplication.data.local.ContactsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun insertData(data: ContactsEntity)

    @Query("SELECT * FROM ContactsEntity")
    fun getAllDataFromContacts(): Flow<List<ContactsEntity>>

    @Query("SELECT * FROM ContactsEntity where id =:id")
    fun getDataFromContacts(id:Int): ContactsEntity
}