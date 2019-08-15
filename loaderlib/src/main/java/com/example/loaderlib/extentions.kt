package com.example.loaderlib

import android.widget.ImageView

fun ImageView.loadImage(url: String, placeholder: Int) = MindLoader.getImageLoader().loadImage(url,this,placeholder)