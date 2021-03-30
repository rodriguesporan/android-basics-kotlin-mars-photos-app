package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgView.load(
            imgUrl?.let { it.toUri().buildUpon().scheme("https").build() }
    ) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    (recyclerView.adapter as PhotoGridAdapter)
            .submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.setImageResource(R.drawable.loading_animation)
            statusImageView.visibility = View.VISIBLE
        }
        MarsApiStatus.ERROR -> {
            statusImageView.setImageResource(R.drawable.ic_connection_error)
            statusImageView.visibility = View.VISIBLE
        }
        MarsApiStatus.DONE -> statusImageView.visibility = View.GONE
    }
}