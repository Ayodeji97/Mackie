package com.example.android.danmack.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**
 * This is the list converter for strings.
 * */
class ListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}