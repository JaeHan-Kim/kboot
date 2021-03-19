package com.rude.kboot.web.controller

import com.rude.kboot.dto.BrandDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

@Controller
class BrandController {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/brands")
    fun createBrandForm(model: Model): String {
        return "brand/brandForm"
    }

    @PostMapping("/brands")
    @ResponseBody
    fun saveBrand(@RequestBody @Valid brandDto: BrandDto, errors: Errors): BrandDto {
        errors.fieldErrors.stream().forEach { e -> println("${e.field}") }
        log.info("$errors")
        log.info("$brandDto")

        return brandDto
    }
}
