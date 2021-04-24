package com.recan.photo_app.data.database

import android.net.Uri
import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {

    @TypeConverter
    fun toEntity(date: Date): Long = date.time

    @TypeConverter
    fun fromEntity(time: Long): Date = Date(time)
}

class UriTypeConverter {

    @TypeConverter
    fun toEntity(uri: Uri): String = uri.toString()

    @TypeConverter
    fun fromEntity(uriString: String): Uri = Uri.parse(uriString)
}