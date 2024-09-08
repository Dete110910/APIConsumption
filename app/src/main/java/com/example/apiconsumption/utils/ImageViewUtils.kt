package com.example.apiconsumption.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadSquareImage(url: String){
    Glide
        .with(this)
        .load(url)
        .into(this)
}