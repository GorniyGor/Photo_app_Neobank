package com.recan.photo_app.presentation.gallery

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.recan.photo_app.App
import com.recan.photo_app.R
import com.recan.photo_app.databinding.GalleryActivityBinding
import com.recan.photo_app.presentation.Utils
import com.recan.photo_app.presentation.base.BaseActivity
import com.recan.photo_app.presentation.model.PhotoModel

class GalleryActivity : BaseActivity<GalleryContract.View, GalleryContract.Presenter>(),
    GalleryContract.View {

    private lateinit var binding: GalleryActivityBinding
    private lateinit var recyclerAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App)
            .getApplicationComponent()
            .galleryFlowComponent()
            .create()
            .getActivityComponent()
            .create()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = GalleryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupScreen()
    }

    private fun setupScreen() {
        title = getString(R.string.gallery_screen_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecycler()
        binding.fullOpenedIvCross.setOnClickListener {
            presenter.onOpenedPhotoCloseClick()
        }
    }

    private fun setupRecycler() {
        binding.recyclerView.apply {
            recyclerAdapter = GalleryAdapter { _, data -> presenter.onItemClick(data) }
            setHasFixedSize(true)
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(this@GalleryActivity, 3)
            addItemDecoration(Utils.GridItemDecoration(this@GalleryActivity))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setItems(list: List<PhotoModel>) {
        recyclerAdapter.submitList(list)
    }

    override fun openPhoto(uri: Uri) {
        setPhotoOpened(true)
        binding.fullOpenedIvPhoto.setImageURI(uri)
    }

    override fun closePhoto() {
        setPhotoOpened(false)
    }

    override fun onBackPressed() {
        if (binding.photoBackground.visibility == View.VISIBLE) presenter.onOpenedPhotoCloseClick()
        else super.onBackPressed()
    }

    private fun setPhotoOpened(isVisible: Boolean) {
        binding.fullOpenedPhotoLayout.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}