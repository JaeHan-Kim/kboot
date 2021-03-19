package com.rude.kboot.web.controller

import com.rude.kboot.dto.Form
import com.rude.kboot.utils.AwsS3Uploader
import com.rude.kboot.utils.FileUtil
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api")
class DataController(
    val fileUtil: FileUtil,
    val awsS3Uploader: AwsS3Uploader
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/save")
    fun saveImage(@RequestBody form: Form): ResponseEntity<Any> {

        log.info("test ${form.images.size}")
        for (image in form.images) {
            fileUtil.createFile(image)
        }
        // fileUtil.createFile(form.image)
        return ResponseEntity
            .ok()
            .body("a")
    }

    @PostMapping("/s3-file-upload")
    fun saveS3File(@RequestBody form: Form): ResponseEntity<Any> {
        for (image in form.images) {
            val fileInfo = awsS3Uploader.s3file(image, null).orElseGet { throw RuntimeException() }
            log.info(awsS3Uploader.putS3(fileInfo.fileName, fileInfo.contentType, fileInfo.fileByte))
        }
        return ResponseEntity.ok().body("s")
    }
}
