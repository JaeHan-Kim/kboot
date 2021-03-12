package com.rude.kboot.web.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "disp/home"
    }

    @GetMapping("/form")
    fun form(model: Model): String {
        model["title"] = "form"
        return "disp/form"
    }
}