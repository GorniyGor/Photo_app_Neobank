package com.recan.photo_app.presentation.action_selection

import com.recan.photo_app.domain.usecases.SavePhotoInfoUseCase
import com.recan.photo_app.domain.usecases.SavePhotoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActionSelectionPresenter @Inject constructor(
    private val router: ActionSelectionContract.Router,
    private val state: ActionSelectionContract.State,
    private val useCaseSavePhoto: SavePhotoUseCase,
    private val useCaseSavePhotoInfo: SavePhotoInfoUseCase
) : ActionSelectionContract.Presenter() {

    override fun onGalleryButtonClick() {
        router.goToGallery()
    }

    override fun onTakePhotoButtonClick() {
        view?.checkCameraPermission(
            acceptAction = { openCamera() }
        )
    }

    override fun onCameraPermissionDenied() {
        view?.showError()
    }

    override fun onCameraPermissionAccepted() {
        openCamera()
    }

    override fun onResultPhoto() {
        view?.showPhotoNamingDialog()
    }

    private fun openCamera() {
        useCaseSavePhoto.createNewImageFile().also { (uriForIntent, fileUri, creationTime) ->
            state.photoURI = fileUri
            state.photoCreationTime = creationTime
            router.goToCamera(fileUri = uriForIntent)
        }
    }

    override fun onPositiveButtonDialogClick(text: String) {
        view?.cleanNamingDialog()
        text.also {
            state.enteredPhotoName = it
            savePhotoWithName(it)
        }
    }

    private fun savePhotoWithName(name: String) {
        val fileUri = state.photoURI

        if (fileUri != null) {

            useCaseSavePhoto.renameImageFile(fileUri = fileUri, name = name)
                .also { renamedUri ->
                    state.photoCreationTime?.let { time ->
                        useCaseSavePhotoInfo.addNewPhoto(name, renamedUri, time)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({}, { view?.showError(it.message) })
                            .clearAtTime()
                    }
                }

            clearState()
        }
    }

    override fun onNegativeButtonDialogClick() {
        view?.cleanNamingDialog()
        state.photoURI?.let { uri ->
            useCaseSavePhoto.deleteImageFile(fileUri = uri)
            clearState()
        } ?: view?.showError()
    }

    private fun clearState() {
        state.clean()
    }

}