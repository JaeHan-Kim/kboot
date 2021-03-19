package com.rude.kboot.utils

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.rude.kboot.dto.FileDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Base64
import java.util.Optional
import java.util.UUID

@Component
class AwsS3Uploader(
    val amazonS3Client: AmazonS3Client
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    fun s3file(base64Image: String, fileName: String?): Optional<FileDto> {
        val imageSrc = base64Image.split(",")

        if (imageSrc.size != 2) {
            return Optional.empty()
        }
        val imageBytes = Base64.getDecoder().decode(imageSrc[1])
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val newFileNm = fileName ?: ("${UUID.randomUUID()}-$now")
        val fileTypes = when {
            imageSrc[0].contains(MediaType.IMAGE_JPEG_VALUE) -> arrayOf(MediaType.IMAGE_JPEG_VALUE, "jpg")
            imageSrc[0].contains(MediaType.IMAGE_PNG_VALUE) -> arrayOf(MediaType.IMAGE_PNG_VALUE, "png")
            imageSrc[0].contains(MediaType.IMAGE_GIF_VALUE) -> arrayOf(MediaType.IMAGE_GIF_VALUE, "gif")
            else -> throw RuntimeException()
        }

        log.info("fileName $newFileNm.${fileTypes[1]} / contentType ${fileTypes[0]} / ${imageBytes.size}")
        return Optional.of(FileDto("image$newFileNm.${fileTypes[1]}", base64Image, fileTypes[0], imageBytes))
    }

    fun upload(base64Image: String, fileName: String? = "", directName: String? = ""): String {
        return ""
    }

    fun putS3(fileName: String, contentType: String, fileByte: ByteArray): String {
        val om = ObjectMetadata()
        om.contentType = contentType
        om.contentLength = fileByte.size.toLong()

        amazonS3Client.putObject(
            PutObjectRequest(bucket, fileName, fileByte.inputStream(), om)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return amazonS3Client.getUrl(bucket, fileName).toString()
    }
}
