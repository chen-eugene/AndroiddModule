package com.example.imageloader.glide

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.example.imageloader.ImageConfig

class GlideImageConfig(builder: Builder) : ImageConfig() {

    /**
     * 0对应DiskCacheStrategy.all,
     * 1对应DiskCacheStrategy.NONE,
     * 2对应DiskCacheStrategy.SOURCE,
     * 3对应DiskCacheStrategy.RESULT
     */
    var cacheStrategy: Int = 0
    /**
     * 请求 url 为空,则使用此图片作为占位符
     */
    var fallback: Int = 0
    /**
     * 图片每个圆角的大小
     */
    var imageRadius: Int = 0
    /**
     * 高斯模糊值, 值越大模糊效果越大
     */
    var blurValue: Int = 0

//        @Deprecated("")
    /**
     * glide用它来改变图形的形状
     */
    var transformation: BitmapTransformation? = null

    var imageViews: Array<ImageView> = arrayOf()
    /**
     * 是否使用淡入淡出过渡动画
     */
    var isCrossFade: Boolean = false
    /**
     * 是否将图片剪切为 CenterCrop
     */
    var isCenterCrop: Boolean = false
    /**
     * 是否将图片剪切为圆形
     */
    var isCircle: Boolean = false
    /**
     * 清理内存缓存
     */
    var isClearMemory: Boolean = false
    /**
     * 清理本地缓存
     */
    var isClearDiskCache: Boolean = false

    init {
        url = builder.url
        imageView = builder.imageView
        placeholder = builder.placeholder
        errorPic = builder.errorPic
        cacheStrategy = builder.cacheStrategy
        fallback = builder.fallback
        imageRadius = builder.imageRadius
        blurValue = builder.blurValue
        transformation = builder.transformation
        imageViews = builder.imageViews
        isCrossFade = builder.isCrossFade
        isCenterCrop = builder.isCenterCrop
        isCircle = builder.isCircle
        isClearMemory = builder.isClearMemory
        isClearDiskCache = builder.isClearDiskCache
    }

    companion object {
        fun build(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder(init: Builder.() -> Unit) {

        init {
            init()
        }

        var url: String? = null
        var res: Int = 0
        var imageView: ImageView? = null
        var placeholder: Int = 0
        var errorPic: Int = 0
        var cacheStrategy: Int = 0
        var fallback: Int = 0
        var imageRadius: Int = 0
        var blurValue: Int = 0

        //        @Deprecated("")
        var transformation: BitmapTransformation? = null

        var imageViews: Array<ImageView> = arrayOf()
        var isCrossFade: Boolean = false
        var isCenterCrop: Boolean = false
        var isCircle: Boolean = false
        var isClearMemory: Boolean = false
        var isClearDiskCache: Boolean = false

        fun url(init: Builder.() -> String) =
                apply { url = init() }

        fun res(init: Builder.() -> Int) =
                apply { res = init() }

        fun imageView(init: Builder.() -> ImageView) =
                apply { imageView = init() }

        fun placeholder(init: Builder.() -> Int) =
                apply { placeholder = init() }

        fun errorPic(init: Builder.() -> Int) =
                apply { errorPic = init() }

        fun cacheStrategy(init: Builder.() -> Int) =
                apply { cacheStrategy = init() }

        fun fallback(init: Builder.() -> Int) =
                apply { fallback = init() }

        fun imageRadius(init: Builder.() -> Int) =
                apply { imageRadius = init() }

        fun blurValue(init: Builder.() -> Int) =
                apply { blurValue = init() }

        fun bitmapTransformation(init: Builder.() -> BitmapTransformation) =
                apply { transformation = init() }

        fun imageViews(init: Builder.() -> Array<ImageView>) =
                apply { imageViews = init() }

        fun isCrossFade(init: Builder.() -> Boolean) =
                apply { isCrossFade = init() }

        fun isCenterCrop(init: Builder.() -> Boolean) =
                apply { isCenterCrop = init() }

        fun isCircle(init: Builder.() -> Boolean) =
                apply { isCircle = init() }

        fun isClearMemory(init: Builder.() -> Boolean) =
                apply { isClearMemory = init() }

        fun isClearDiskCache(init: Builder.() -> Boolean) =
                apply { isClearDiskCache = init() }

        fun build(): GlideImageConfig {
            return GlideImageConfig(this)
        }
    }

}