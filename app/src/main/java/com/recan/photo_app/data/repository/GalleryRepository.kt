package com.recan.photo_app.data.repository

import com.recan.photo_app.data.database.PhotoInfoDao
import com.recan.photo_app.data.model.mapper.PhotoEntityMapper.toDbEntity
import com.recan.photo_app.data.model.mapper.PhotoEntityMapper.toModel
import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.domain.repository.IGalleryRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val photoDao: PhotoInfoDao
) : IGalleryRepository {

    override fun addNewPhoto(photoInfo: PhotoInfo): Completable =
        photoDao.insertPhoto(
            photo = photoInfo.toDbEntity()
        )

    override fun getPhotos(): Single<List<PhotoInfo>> =
        photoDao.getPhotos().firstOrError().map { list -> list.map { it.toModel() } }
}