package com.example.imageloader.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.Preconditions
import com.example.imageloader.BaseImageLoaderStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GlideImageLoaderStrategy : BaseImageLoaderStrategy<GlideImageConfig>, GlideAppliesOptions {

    override fun loadImage(ctx: Context, config: GlideImageConfig) {

        Preconditions.checkNotNull<Any>(ctx, "Context is required")
        Preconditions.checkNotNull<Any>(config, "GlideImageConfigImpl is required")
//        Preconditions.checkNotNull(config.url, "Url is required")
        Preconditions.checkNotNull<Any>(config.imageView, "ImageView is required")

        val requests = GlideModule.with(ctx)

        //如果context是activity则自动使用Activity的生命周期
        val glideRequest = if (!config.url.isNullOrBlank()) {
            //加载网络资源
            requests.load(config.url)
        } else if (config.res != 0) {
            //加载资源文件
            requests.load(config.res)
        } else {
            requests.load(config.errorPic)
        }


//        val glideRequest = if (config.url.isNullOrBlank()) {
////            if (config.placeholder != 0) {
////                requests.load(config.placeholder)
////            }
////            if (config.errorPic != 0) {
////                requests.load(config.errorPic)
////            }
//            requests.load("")
//        } else {
//            requests.load(config.url)
//        }

        when (config.cacheStrategy) {
        //缓存策略
            0 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
            1 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE)
            2 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            3 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA)
            4 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            else -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        if (config.isCrossFade) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade())
        }

        if (config.isCenterCrop) {
            glideRequest.centerCrop()
        }

        if (config.isCircle) {
            glideRequest.circleCrop()
        }

        if (config.imageRadius > 0) {
            glideRequest.transform(RoundedCorners(config.imageRadius))
        }

        if (config.blurValue > 0) {
            glideRequest.transform(BlurTransformation(config.blurValue))
        }

        if (config.transformation != null) {//glide用它来改变图形的形状
            glideRequest.transform(config.transformation!!)
        }

        if (config.placeholder != 0)
        //设置占位符
            glideRequest.placeholder(config.placeholder)

        if (config.errorPic != 0)
        //设置错误的图片
            glideRequest.error(config.errorPic)

        if (config.fallback != 0)
        //设置请求 url 为空图片
            glideRequest.fallback(config.fallback)


        glideRequest
                .into(config.imageView!!)
    }

    override fun clear(ctx: Context, config: GlideImageConfig) {
        Preconditions.checkNotNull<Any>(ctx, "Context is required")
        Preconditions.checkNotNull<Any>(config, "GlideImageConfigImpl is required")

        if (config.imageViews.isNotEmpty()) {//取消在执行的任务并且释放资源
            for (imageView in config.imageViews) {
                Glide.get(ctx).requestManagerRetriever.get(ctx).clear(imageView)
            }
        }

        if (config.isClearDiskCache) {//清除本地缓存
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe { Glide.get(ctx).clearDiskCache() }
        }

        if (config.isClearMemory) {//清除内存缓存
            Observable.just(0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Glide.get(ctx).clearMemory() }
        }

    }


    override fun applyGlideOptions(context: Context, builder: GlideBuilder) {
        Timber.w("applyGlideOptions")
    }
}
