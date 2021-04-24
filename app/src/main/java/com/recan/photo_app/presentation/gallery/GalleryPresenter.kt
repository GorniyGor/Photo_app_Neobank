package com.recan.photo_app.presentation.gallery

import com.recan.photo_app.domain.usecases.GetPhotoListUseCase
import com.recan.photo_app.presentation.model.PhotoModel
import com.recan.photo_app.presentation.model.mapper.PhotoInfoMapper.toModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GalleryPresenter @Inject constructor(
    private val useCase: GetPhotoListUseCase
) : GalleryContract.Presenter() {

    override fun onBindView(view: GalleryContract.View) {
        useCase.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> view.setItems(list.map { it.toModel() }) },
                { view.showError(it.message) }
            )
            .clearAtTime()
    }

    override fun onItemClick(model: PhotoModel) {
        view?.openPhoto(uri = model.uri)
    }

    override fun onOpenedPhotoCloseClick() {
        view?.closePhoto()
    }

}