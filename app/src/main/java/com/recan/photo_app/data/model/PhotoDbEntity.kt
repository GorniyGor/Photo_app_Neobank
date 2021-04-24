package com.recan.photo_app.data.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.recan.photo_app.data.database.DateTypeConverter
import com.recan.photo_app.data.database.UriTypeConverter
import java.util.*

@Entity(tableName = "Photo")
@TypeConverters(
    DateTypeConverter::class,
    UriTypeConverter::class
)
data class PhotoDbEntity(
    @ColumnInfo val name: String,
    @ColumnInfo val creationDate: Date,
    @ColumnInfo val uri: Uri,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

