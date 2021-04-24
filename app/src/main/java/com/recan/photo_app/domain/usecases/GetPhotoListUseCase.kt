package com.recan.photo_app.domain.usecases

import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.domain.repository.IGalleryRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPhotoListUseCaseImpl @Inject constructor(
    private val repository: IGalleryRepository
) : GetPhotoListUseCase {

    override fun getPhotos(): Single<List<PhotoInfo>> = repository.getPhotos()

}

interface GetPhotoListUseCase {
    fun getPhotos(): Single<List<PhotoInfo>>
}