package com.example.mymovies.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mymovies.BuildConfig
import javax.inject.Inject

class DownloadImageHelper @Inject constructor(
        private val context: Context
) {
    fun loadImage(imageView: ImageView, imagePath: String) {
        Glide.with(imageView.context)
                .load(BuildConfig.IMAGE_URL + imagePath)
                .into(imageView)
    }

    fun getDrawable(imagePath: String): Drawable =
            Glide.with(context)
                    .load(BuildConfig.IMAGE_URL + imagePath)
                    .submit()
                    .get()
}