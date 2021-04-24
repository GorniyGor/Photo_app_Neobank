package com.recan.photo_app.domain.usecases

import android.net.Uri
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.domain.repository.IGalleryRepository
import org.junit.Test
import java.util.*

class SavePhotoInfoUseCaseImplTest {

    private val repository: IGalleryRepository = mock()
    private val useCase = SavePhotoInfoUseCaseImpl(
        repository = repository
    )

    @Test
    fun `add new photo item`() {
        val photoUri: Uri = mock()
        val creationDate: Date = mock()

        useCase.addNewPhoto(name = "test name", uri = photoUri, creationTime = creationDate)

        verify(repository).addNewPhoto(
            photoInfo = PhotoInfo(name = "test name", uri = photoUri, creationTime = creationDate)
        )
    }
}