package com.aainur.externalpooling.services

import com.aainur.externalpooling.utils.ExternalRequestBuilder
import khttp.responses.Response
import org.springframework.stereotype.Service

@Service
class ExternalPoolingService(
        private val externalRequestBuilder: ExternalRequestBuilder,
        private val resultSender: ResultSender) {

    // ToDo: keep map with poolers for every request
    private var pooler: Pooler = Pooler(resultSender)

    fun runPooling(service: String, currency: String) {
        val url = externalRequestBuilder.buildUrl(service)
        pooler.run(url, currency)
    }

    fun cancelPooling(service: String, currency: String) {
        pooler.cancel()
    }

    fun process(response: Response) {
        val obj = response.jsonObject
    }

    fun handleError(response: Response) {
        val error = response.text
    }
}
