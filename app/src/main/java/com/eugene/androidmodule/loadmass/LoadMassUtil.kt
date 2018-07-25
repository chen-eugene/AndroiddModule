package com.eugene.androidmodule.loadmass

import android.content.Context
import android.provider.MediaStore
import java.io.File

class LoadMassUtil {
    private val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA
    )

    private val sortOrder = MediaStore.Images.Media.DATE_TAKEN + "DESC"

    fun queryImage(context: Context) {
        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                null, null, sortOrder)
        //一个辅助集合，防止同一目录被扫描多次
        val dirPaths = HashSet<String>()

        while (cursor.moveToNext()) {
            // 获取图片的路径
            val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            val bucketName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME))

//            if (TextUtils.isEmpty(allFolderItem.coverImagePath)) {
//                allFolderItem.coverImagePath = path
//            }

            val parentFile = File(path).parentFile ?: continue

            val dirPath = parentFile.absolutePath

//            var folderItem: PicFolderItem? = null
            // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
            if (dirPaths.contains(dirPath)) {
                continue
            } else {
                dirPaths.add(dirPath)

                var isNew = true
                //判断一下是否dirPath不同，但是bucketName相同
//                for (item in picList) {
//                    if (item.name.equals(bucketName)) {
//                        folderItem = item
//                        item.addParentPath(dirPath)
//                        isNew = false
//                        break
//                    }
//                }

//                if (isNew) {
//                    folderItem = PicFolderItem()
//                    folderItem!!.coverImagePath = path
//                    folderItem!!.name = bucketName
//                    folderItem!!.addParentPath(dirPath)
//                }
            }

            val array = parentFile.list { dir, filename ->
                (filename.endsWith(".jpg")
                        || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
            }

            val arrayCount = array?.size ?: 0
//            folderItem.count += arrayCount
//            if (!picList.contains(folderItem) && arrayCount > 0) {
//                picList.add(folderItem)
//            }
        }

        cursor.close()

    }

}