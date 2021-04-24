package com.recan.photo_app.domain.entity

import android.net.Uri
import java.util.*

data class PhotoInfo(
    val name: String,
    val uri: Uri,
    val creationTime: Date
)