package com.recan.photo_app.presentation.model

import android.net.Uri
import java.util.*

data class PhotoModel(
    val name: String,
    val creationDate: Date,
    val uri: Uri
)