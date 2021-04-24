package com.recan.photo_app.presentation.action_selection

import android.Manifest
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.recan.photo_app.App
import com.recan.photo_app.R
import com.recan.photo_app.databinding.ActionSelectionActivityBinding
import com.recan.photo_app.databinding.NameDialogEnterBinding
import com.recan.photo_app.presentation.action_selection.ActionSelectionContract.Companion.PERMISSION_REQUEST_CAMERA
import com.recan.photo_app.presentation.base.BaseActivity

class ActionSelectionActivity :
    BaseActivity<ActionSelectionContract.View, ActionSelectionContract.Presenter>(),
    ActionSelectionContract.View {

    private lateinit var binding: ActionSelectionActivityBinding
    private lateinit var photoNameDialog: AlertDialog
    private lateinit var dialogEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App)
            .getApplicationComponent()
            .actionSelectionFlowComponent()
            .create()
            .getActivityComponent()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActionSelectionActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        title = getString(R.string.action_screen_title)
        setupActions()
        setupDialog()
    }

    private fun setupActions() {
        binding.galleryButton.setOnClickListener { presenter.onGalleryButtonClick() }
        binding.takePhotoButton.setOnClickListener { presenter.onTakePhotoButtonClick() }
    }

    override fun checkCameraPermission(
        acceptAction: () -> Unit
    ) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {

            acceptAction.invoke()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.onCameraPermissionAccepted()
            } else {
                presenter.onCameraPermissionDenied()
            }
        }
    }

    private fun setupDialog() {
        val editTextLayout = NameDialogEnterBinding.inflate(layoutInflater)
        dialogEditText = editTextLayout.etName
        photoNameDialog = AlertDialog.Builder(this)
            .setView(editTextLayout.root)
            .setTitle(getString(R.string.action_screen_dialog_title))
            .setCancelable(false)
            .create()
            .apply {
                setButton(
                    BUTTON_POSITIVE,
                    getString(R.string.action_screen_dialog_positive_action)
                ) { _, _ ->
                    presenter.onPositiveButtonDialogClick(
                        text = dialogEditText.text.toString()
                    )

                }
                setButton(
                    BUTTON_NEGATIVE,
                    getString(R.string.action_screen_dialog_negative_action)
                ) { _, _ ->
                    presenter.onNegativeButtonDialogClick()
                }
            }
    }

    override fun showPhotoNamingDialog() {
        photoNameDialog.show()
    }

    override fun cleanNamingDialog() {
        dialogEditText.text.clear()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ActionSelectionContract.REQUEST_CODE_TAKE_PICTURE && resultCode == RESULT_OK) {
            presenter.onResultPhoto()
        }
    }
}