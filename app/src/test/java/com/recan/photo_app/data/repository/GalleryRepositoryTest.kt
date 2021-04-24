package com.recan.photo_app.data.repository

import android.net.Uri
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.recan.photo_app.data.database.PhotoInfoDao
import com.recan.photo_app.data.model.PhotoDbEntity
import com.recan.photo_app.domain.entity.PhotoInfo
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import java.util.*

class GalleryRepositoryTest {

    private val photoDao: PhotoInfoDao = mock()
    private val repository = GalleryRepository(
        photoDao = photoDao
    )

    private val photoUri: Uri = mock()
    private val creationDate: Date = mock()
    private val testName = "test name"

    @Test
    fun `add new photo to db`() {
        val photoInfo = PhotoInfo(name = testName, uri = photoUri, creationTime = creationDate)
        val photoDbEntity =
            PhotoDbEntity(name = testName, uri = photoUri, creationDate = creationDate)

        whenever(photoDao.insertPhoto(photo = photoDbEntity)) doReturn Completable.complete()

        repository.addNewPhoto(photoInfo = photoInfo).test()

        verify(photoDao).insertPhoto(photoDbEntity)
    }

    @Test
    fun `get photo list from db`() {
        val dbPhotoList = listOf(
            PhotoDbEntity(testName, creationDate, photoUri),
            PhotoDbEntity(testName, creationDate, photoUri)
        )

        whenever(photoDao.getPhotos()) doReturn Observable.just(dbPhotoList)

        repository.getPhotos().test()
            .assertValue(
                listOf(
                    PhotoInfo(testName, photoUri, creationDate),
                    PhotoInfo(testName, photoUri, creationDate)
                )
            )
    }
}