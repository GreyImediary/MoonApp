package com.example.moonapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

/**
 * Класс, содержащий методы, необходимые для загрузки и управления файлами
 * */
object DownloadUtils {

    /**
     * Скачивает файл при помощи [DownloadManager]
     * [url] является ссылкой на скачиваемый файл
     * [fileName] - имя файла, в который будет загружен файл
     * [title] - заголовок уведомления
     * [context] необходим для получения [DownloadManager]
     * */
    fun downloadFile(url: String, fileName: String, title: String, context: Context?) {
        val uri = Uri.parse(url)

        val dm = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(
            DownloadManager.Request(uri)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setTitle(title)
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        )
    }
}