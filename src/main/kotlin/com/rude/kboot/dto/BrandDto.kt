package com.rude.kboot.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

data class BrandDto(
    var brandSeq: Long,
    var useYn: Boolean,
    @get:NotBlank(message = "brandNm is not blank")
    var brandNm: String,
    @get:NotBlank(message = "brandEngNm is not blank")
    var brandEngNm: String,
    var dispYn: Boolean,
    @get:PositiveOrZero(message = "dispNo is more than 0")
    var dispNo: Int,
    @get:NotBlank(message = "explain is not blank")
    var explain: String
)
