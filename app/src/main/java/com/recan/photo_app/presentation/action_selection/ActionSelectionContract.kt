package com.recan.photo_app.presentation.action_selection

import android.net.Uri
import android.os.Parcelable
import com.recan.photo_app.presentation.base.BasePresenter
import com.recan.photo_app.presentation.base.PresentationView
import kotlinx.android.parcel.Parcelize
import java.net.URI
import java.util.*

interface ActionSelectionContract {

    companion object {
        const val REQUEST_CODE_TAKE_PICTURE = 123
        const val PERMISSION_REQUEST_CAMERA = 0
    }

    interface View : PresentationView {
        fun showPhotoNamingDialog()
        fun cleanNamingDialog()

        fun checkCameraPermission(acceptAction: () -> Unit)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun onGalleryButtonClick()
        abstract fun onTakePhotoButtonClick()
        abstract fun onPositiveButtonDialogClick(text: String)
        abstract fun onNegativeButtonDialogClick()
        abstract fun onCameraPermissionDenied()
        abstract fun onCameraPermissionAccepted()
        abstract fun onResultPhoto()
    }

    interface Router {
        fun goToGallery()
        fun goToCamera(fileUri: Uri)
    }


    @Parcelize
    data class State(
        var photoURI: URI? = null,
        var photoCreationTime: Date? = null,
        var enteredPhotoName: String? = null
    ) : Parcelable {

        fun clean() {
            photoURI = null
            photoCreationTime = null
            enteredPhotoName = null
        }
    }
}