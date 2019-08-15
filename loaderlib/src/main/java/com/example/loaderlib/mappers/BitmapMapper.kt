package com.example.mindloader.core.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapMapper : DataStreamMapper<ByteArray,Bitmap> {
    override fun mapData(input: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(input,0,input.size)
    }
}