package com.example.imageloader.glide

import android.graphics.Bitmap
import android.support.annotation.IntRange

import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

import java.security.MessageDigest

/**
 * ================================================
 * 高斯模糊
 *
 *
 * Created by JessYan on 03/04/2018 15:14
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class BlurTransformation(@IntRange(from = 0) radius: Int) : BitmapTransformation() {

    private val ID = BlurTransformation::class.java.name
    private val ID_BYTES = ID.toByteArray(Key.CHARSET)
    val DEFAULT_RADIUS = 15

    private var mRadius = DEFAULT_RADIUS

    init {
        mRadius = radius
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)

    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
        return FastBlur.doBlur(toTransform, mRadius, true)
    }

    override fun equals(other: Any?): Boolean {
        return other is BlurTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }
}
