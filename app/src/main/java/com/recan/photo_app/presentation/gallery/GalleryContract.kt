package com.recan.photo_app.presentation.gallery

import android.net.Uri
import com.recan.photo_app.presentation.base.BasePresenter
import com.recan.photo_app.presentation.base.PresentationView
import com.recan.photo_app.presentation.model.PhotoModel
import io.reactivex.Observable

interface GalleryContract {

    companion object {
        const val PHOTO_DATE_CREATION_PATTERN = "dd.MM.yyyy HH:mm:ss"
    }

    interface View : PresentationView {
        fun setItems(list: List<PhotoModel>)
        fun openPhoto(uri: Uri)
        fun closePhoto()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun onItemClick(model: PhotoModel)
        abstract fun onOpenedPhotoCloseClick()
    }
}