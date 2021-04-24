package com.recan.photo_app.domain.usecases

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.net.URI
import java.util.*
import javax.inject.Inject

class SavePhotoUseCaseImpl @Inject constructor(
    private val context: Context
) : SavePhotoUseCase {

    companion object {
        const val FILE_TEMP_NAME_PATTERN = "Photo_app_tempJPEG_"
        const val FILE_PROVIDER_PACKAGE = "com.recan.photo_app.fileprovider"
    }

    override fun createNewImageFile(): FileInfo {

        val creationTime = Date()
        val file = createFile(name = FILE_TEMP_NAME_PATTERN)

        return FileInfo(uriForIntent = file.getUri(), file.toURI(), dateCreation = creationTime)
    }

    private fun createFile(name: String): File {
        val storageDir: File =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: context.filesDir
        val correctedName =
            if (name.length < 3) Array(3) { name.getOrNull(it) ?: '_' }.toString() else name
        return File.createTempFile(
            correctedName,
            ".jpg",
            storageDir
        )
    }

    private fun File.getUri(): Uri =
        FileProvider.getUriForFile(
            context,
            FILE_PROVIDER_PACKAGE,
            this
        )

    override fun deleteImageFile(fileUri: URI) {
        File(fileUri).delete()
    }

    override fun renameImageFile(fileUri: URI, name: String): Uri {
        val renamedFile = createFile(name = name)
        return renamedFile.let {
            File(fileUri).renameTo(renamedFile)
            it.getUri()
        }
    }
}

data class FileInfo(
    val uriForIntent: Uri,
    val fileUri: URI,
    val dateCreation: Date
)

interface SavePhotoUseCase {
    fun createNewImageFile(): FileInfo
    fun deleteImageFile(fileUri: URI)
    fun renameImageFile(fileUri: URI, name: String): Uri
}