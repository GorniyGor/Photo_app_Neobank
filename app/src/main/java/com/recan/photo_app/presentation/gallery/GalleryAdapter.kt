package com.recan.photo_app.presentation.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.recan.photo_app.databinding.PhotoItemBinding
import com.recan.photo_app.presentation.model.PhotoModel
import java.text.SimpleDateFormat
import java.util.*

class GalleryAdapter(
    private val itemClickListener: (view: View, data: PhotoModel) -> Unit
) : ListAdapter<PhotoModel, GalleryAdapter.ViewHolder>(PhotoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PhotoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false).root
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = PhotoItemBinding.bind(itemView)
        private var data: PhotoModel? = null
        private val dateFormatter =
            SimpleDateFormat(GalleryContract.PHOTO_DATE_CREATION_PATTERN, Locale.getDefault())

        init {
            itemView.setOnClickListener { v ->
                data?.let { itemClickListener(v, it) }
            }
        }

        fun bind(data: PhotoModel) {
            this.data = data
            binding.ivThumbnail.setImageURI(data.uri)
            binding.tvName.text = data.name
            binding.tvCreationTime.text = dateFormatter.format(data.creationDate)
        }
    }

    class PhotoItemDiffCallback : DiffUtil.ItemCallback<PhotoModel>() {
        override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem == newItem
        }

    }
}