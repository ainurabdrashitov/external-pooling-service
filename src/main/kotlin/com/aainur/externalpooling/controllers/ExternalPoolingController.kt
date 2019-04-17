package com.aainur.externalpooling.controllers

import com.aainur.externalpooling.services.ExternalPoolingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/external-pooling")
class ExternalPoolingController(private val externalPoolingService: ExternalPoolingService) {

    @GetMapping("/get-balance/run/{service}/{currency}")
    fun runPooling(@PathVariable service: String, @PathVariable currency: String) {
        externalPoolingService.runPooling(service, currency)
    }
}
