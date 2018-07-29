package com.example.imageloader

import android.content.Context

/**
 * ================================================
 * 图片加载策略,实现 [BaseImageLoaderStrategy]
 * 并通过 [ImageLoader.setLoadImgStrategy] 配置后,才可进行图片请求
 *
 * ================================================
 */
interface BaseImageLoaderStrategy<in T : ImageConfig> {
    /**
     * 加载图片
     *
     * @param ctx
     * @param config
     */
    fun loadImage(ctx: Context, config: T)

    /**
     * 停止加载
     *
     * @param ctx
     * @param config
     */
    fun clear(ctx: Context, config: T)
}
