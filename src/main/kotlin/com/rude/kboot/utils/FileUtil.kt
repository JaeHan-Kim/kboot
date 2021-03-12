package com.rude.kboot.utils

import org.apache.commons.io.FileUtils
import org.codehaus.groovy.tools.shell.util.Logger.io
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.File
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.UUID




@Component
class FileUtil {

    private val log = LoggerFactory.getLogger(this.javaClass);

    @Value("\${resources.location}")
    lateinit var resourcesLocation: String
    fun createFile(image: String): String {

        println(image)
        var path: String = "${resourcesLocation}test"

        val imageSrc = image.split(",")

        println(imageSrc.size)

        if (imageSrc.size != 2) {
            return "";
        }

        val dir = File(path)
        log.info("dir.exists()) ${dir.exists()}")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val imageBytes = Base64.getDecoder().decode(imageSrc[1])
        val newFileNm = ("${UUID.randomUUID()}-${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))}");

        // val imgIs = ByteArrayInputStream(imageBytes)
        var fileType:String

        if (imageSrc[0].contains(MediaType.IMAGE_JPEG_VALUE)) {
            fileType = "jpeg"
        } else if (imageSrc[0].contains(MediaType.IMAGE_PNG_VALUE)) {
            fileType = "png"
        } else if (imageSrc[0].contains(MediaType.IMAGE_GIF_VALUE)) {
            fileType = "gif"
        } else {
            throw RuntimeException()
        }

        log.info("ds $newFileNm / $fileType / ${imageBytes.size}")
        log.info("file full path -> $path$newFileNm.$fileType")
        FileUtils.writeByteArrayToFile(File("$path/$newFileNm.$fileType"), imageBytes);
        return ""
    }
}