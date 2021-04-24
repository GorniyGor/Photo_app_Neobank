package com.recan.photo_app.presentation.gallery

import android.net.Uri
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.recan.photo_app.domain.entity.PhotoInfo
import com.recan.photo_app.domain.usecases.GetPhotoListUseCase
import com.recan.photo_app.presentation.model.PhotoModel
import com.recan.photo_app.test_core.AndroidTestSchedulerRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class GalleryPresenterTest {

    @Rule
    @JvmField
    var androidScheduler = AndroidTestSchedulerRule()

    private val useCase: GetPhotoListUseCase = mock()
    private lateinit var presenter: GalleryPresenter

    private val view: GalleryContract.View = mock()

    @Before
    fun setup() {
        presenter = GalleryPresenter(
            useCase = useCase
        )

        whenever(useCase.getPhotos()) doReturn Single.never()
    }

    @Test
    fun `set photo list WHEN view is bound AND no error`() {
        val photoUri: Uri = mock()
        val creationDate: Date = mock()
        val testName = "test name"

        val photoInfoList = listOf(
            PhotoInfo(testName, photoUri, creationDate),
            PhotoInfo(testName, photoUri, creationDate)
        )
        val photoModelList = listOf(
            PhotoModel(testName, creationDate, photoUri),
            PhotoModel(testName, creationDate, photoUri)
        )

        whenever(useCase.getPhotos()) doReturn Single.just(photoInfoList)

        presenter.bindView(view)

        verify(view).setItems(photoModelList)
    }

    @Test
    fun `set photo list WHEN view is bound AND with error`() {
        whenever(useCase.getPhotos()) doReturn Single.error(Throwable("error message"))

        presenter.bindView(view = view)

        verify(view).showError(message = "error message")
    }

    @Test
    fun `open photo WHEN list item is clicked`() {
        val testUri: Uri = mock()
        val clickedItemModel = PhotoModel(name = "", creationDate = mock(), uri = testUri)

        presenter.bindView(view)

        presenter.onItemClick(model = clickedItemModel)

        verify(view).openPhoto(uri = testUri)

    }

    @Test
    fun `close photo WHEN close icon is clicked`() {
        presenter.bindView(view)

        presenter.onOpenedPhotoCloseClick()

        verify(view).closePhoto()
    }
}