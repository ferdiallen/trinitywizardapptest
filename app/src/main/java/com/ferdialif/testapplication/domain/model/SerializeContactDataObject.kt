package com.ferdialif.testapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class ContactsSerializer(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = "",
    val email: String = ""
)
