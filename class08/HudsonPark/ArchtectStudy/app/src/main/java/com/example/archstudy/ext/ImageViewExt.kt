package com.example.archstudy.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.archstudy.R

@BindingAdapter("image")
fun imageViewBindingAdapter(imageView: ImageView, image: String) {

    Glide.with(imageView.context)
        .load(image)
        .error(R.drawable.no_image)
        .into(imageView)
}
