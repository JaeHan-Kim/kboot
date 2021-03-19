package com.rude.kboot.dto

data class FileDto(
    val fileName: String,
    val base64data: String,
    val contentType: String,
    val fileByte: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileDto

        if (fileName != other.fileName) return false
        if (base64data != other.base64data) return false
        if (contentType != other.contentType) return false
        if (!fileByte.contentEquals(other.fileByte)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + base64data.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + fileByte.contentHashCode()
        return result
    }
}
