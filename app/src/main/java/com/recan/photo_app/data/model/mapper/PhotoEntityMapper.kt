package com.recan.photo_app.data.model.mapper

import com.recan.photo_app.data.model.PhotoDbEntity
import com.recan.photo_app.domain.entity.PhotoInfo

object PhotoEntityMapper {

    fun PhotoInfo.toDbEntity(): PhotoDbEntity =
        PhotoDbEntity(name, creationTime, uri)

    fun PhotoDbEntity.toModel(): PhotoInfo =
        PhotoInfo(name, uri, creationDate)
}