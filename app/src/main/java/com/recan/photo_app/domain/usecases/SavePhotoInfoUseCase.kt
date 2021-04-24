package com.recan.photo_app.domain.usecases

import android.net.Uri
import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.domain.repository.IGalleryRepository
import io.reactivex.Completable
import java.util.*
import javax.inject.Inject

class SavePhotoInfoUseCaseImpl @Inject constructor(
    private val repository: IGalleryRepository
) : SavePhotoInfoUseCase {

    override fun addNewPhoto(name: String, uri: Uri, creationTime: Date): Completable {
        return repository.addNewPhoto(
            photoInfo = PhotoInfo(name, uri, creationTime)
        )
    }
}

interface SavePhotoInfoUseCase {
    fun addNewPhoto(name: String, uri: Uri, creationTime: Date): Completable
}