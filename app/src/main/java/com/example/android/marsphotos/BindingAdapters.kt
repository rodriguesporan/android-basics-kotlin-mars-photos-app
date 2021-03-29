package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgView.load(
            imgUrl?.let { it.toUri().buildUpon().scheme("https").build() }
    ) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}