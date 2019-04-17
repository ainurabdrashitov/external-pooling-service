package com.aainur.externalpooling.services

import com.aainur.externalpooling.builders.ExternalRequestBuilder
import khttp.responses.Response
import org.springframework.stereotype.Service

@Service
class ExternalPoolingService(val externalRequestBuilder: ExternalRequestBuilder) {

    fun runPooling(service: String, currency: String) {

    }

    fun createPoolingActor() {

    }

    fun pool(service: String, currency: String) {
        val url = externalRequestBuilder.buildUrl(service)
        val responce = khttp.get(
                url = url,
                params = mapOf("market" to String.format("usdt-%s", currency)))
        if (responce.statusCode == 200) {
            process(responce)
        } else {
            handleError(responce)
        }
    }

    fun process(response: Response) {
        val obj = response.jsonObject
    }

    fun handleError(response: Response) {
        val error = response.text
    }
}
