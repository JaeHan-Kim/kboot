package com.rude.kboot.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    private val log = LoggerFactory.getLogger(this.javaClass);

    @Value("\${resources.location}")
    lateinit var resourcesLocation: String

    @Value("\${resources.uri-path}")
    lateinit var resourcesUriPath: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        log.info("test1 $resourcesLocation")
        log.info("test2 $resourcesUriPath")
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///tmp/image/");
    }

}