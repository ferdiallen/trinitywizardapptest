package com.ferdialif.testapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ferdialif.testapplication.domain.model.ContactsSerializer

@Entity
data class ContactsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val dateBirth: String = "",
    val email: String = ""
)

fun ContactsSerializer.toContactsEntity(): ContactsEntity {
    return ContactsEntity(
        firstName = firstName,
        lastName = lastName,
        email = email,
        dateBirth = dob
    )
}
