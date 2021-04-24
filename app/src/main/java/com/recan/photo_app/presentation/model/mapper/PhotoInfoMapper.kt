package com.recan.photo_app.presentation.model.mapper

import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.presentation.model.PhotoModel

object PhotoInfoMapper {

    fun PhotoInfo.toModel(): PhotoModel =
        PhotoModel(name, creationTime, uri)
}