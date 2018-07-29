package com.example.imageloader

import android.widget.ImageView

/**
 * ================================================
 * 这里是图片加载配置信息的基类,定义一些所有图片加载框架都可以用的通用参数
 * 每个 [BaseImageLoaderStrategy] 应该对应一个 [ImageConfig] 实现类
 *
 *
 * Created by JessYan on 8/5/16 15:19
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
open class ImageConfig {
    var url: String? = null
    var res: Int = 0
    var imageView: ImageView? = null
    var placeholder: Int = 0
    var errorPic: Int = 0
}
