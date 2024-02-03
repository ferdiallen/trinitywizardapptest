package com.ferdialif.testapplication.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ferdialif.testapplication.data.database.ContactsDatabase
import com.ferdialif.testapplication.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ContactsDatabase::class.java, "contact_list").build()

    @Provides
    @Singleton
    fun providesContactRepository(database: ContactsDatabase) = ContactRepository(database)
}