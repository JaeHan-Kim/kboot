package com.rude.kboot.utils

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.io.File
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Base64
import java.util.UUID

@Component
class FileUtil {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${resources.location}")
    lateinit var resourcesLocation: String

    fun createFile(image: String): String {

        println(image)
        val path = "${resourcesLocation}test"

        val imageSrc = image.split(",")

        println(imageSrc.size)

        if (imageSrc.size != 2) {
            return ""
        }

        val dir = File(path)
        log.info("dir.exists()) ${dir.exists()}")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val imageBytes = Base64.getDecoder().decode(imageSrc[1])
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val newFileNm = ("${UUID.randomUUID()}-$now")
        val fileType = when {
            imageSrc[0].contains(MediaType.IMAGE_JPEG_VALUE) -> "jpeg"
            imageSrc[0].contains(MediaType.IMAGE_PNG_VALUE) -> "png"
            imageSrc[0].contains(MediaType.IMAGE_GIF_VALUE) -> "gif"
            else -> throw RuntimeException()
        }

        log.info("ds $newFileNm / $fileType / ${imageBytes.size}")
        log.info("file full path -> $path$newFileNm.$fileType")
        FileUtils.writeByteArrayToFile(File("$path/$newFileNm.$fileType"), imageBytes)
        return ""
    }
}
