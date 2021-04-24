package com.recan.photo_app.presentation.action_selection

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.recan.photo_app.presentation.gallery.GalleryActivity
import javax.inject.Inject
import javax.inject.Named

class ActionSelectionRouter @Inject constructor(
    @Named("activityContext")
    private val activityContext: Context
) : ActionSelectionContract.Router {

    override fun goToGallery() {
        activityContext.startActivity(Intent(activityContext, GalleryActivity::class.java))
    }

    override fun goToCamera(fileUri: Uri) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

        (activityContext as? Activity)
            ?.startActivityForResult(
                cameraIntent,
                ActionSelectionContract.REQUEST_CODE_TAKE_PICTURE
            )
    }

}