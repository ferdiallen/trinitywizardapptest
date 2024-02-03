package com.ferdialif.testapplication.utils

import android.content.Context
import androidx.annotation.RawRes
import com.ferdialif.testapplication.domain.model.ContactsSerializer
import kotlinx.serialization.json.Json

fun serializeData(context: Context, @RawRes data: Int): List<ContactsSerializer> {
    val parsedData = context.resources.openRawResource(data).bufferedReader().use { it.readText() }
    return Json.decodeFromString<List<ContactsSerializer>>(parsedData)
}