package com.recan.photo_app.domain.repository

import com.recan.photo_app.domain.entity.PhotoInfo
import io.reactivex.Completable
import io.reactivex.Single

interface IGalleryRepository {
    fun addNewPhoto(photoInfo: PhotoInfo): Completable
    fun getPhotos(): Single<List<PhotoInfo>>
}