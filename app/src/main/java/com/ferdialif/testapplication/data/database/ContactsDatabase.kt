package com.ferdialif.testapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ferdialif.testapplication.data.dao.ContactDao
import com.ferdialif.testapplication.data.local.ContactsEntity

@Database(entities = [ContactsEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun dao(): ContactDao
}