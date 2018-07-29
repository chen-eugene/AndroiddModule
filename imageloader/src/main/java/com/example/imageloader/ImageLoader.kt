package com.example.imageloader

import android.content.Context
import com.example.imageloader.glide.GlideImageConfig
import com.example.imageloader.glide.GlideImageLoaderStrategy

object ImageLoader {

    private val loadImgStrategy = GlideImageLoaderStrategy()

    /**
     * 加载图片
     *
     * @param context
     * @param config
     * @param <T>
    </T> */
    fun loadImage(context: Context, config: GlideImageConfig) {
        loadImgStrategy.loadImage(context, config)
    }

    /**
     * 停止加载或清理缓存
     *
     * @param context
     * @param config
     * @param <T>
    </T> */
    fun clear(context: Context, config: GlideImageConfig) {
        loadImgStrategy.clear(context, config)
    }
}
