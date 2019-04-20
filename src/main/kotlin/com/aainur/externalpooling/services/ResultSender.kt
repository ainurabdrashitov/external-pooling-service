package com.aainur.externalpooling.services

import com.aainur.externalpooling.utils.Logger
import org.springframework.stereotype.Service

@Service
class ResultSender {

    fun sendResult(currency: String, exchange: String, value: String) {
        val url = "localhost:8080"
        val responce = khttp.post(
                url = url,
                params = mapOf(
                        "market" to String.format("usdt-%s", currency,
                        "exchange" to exchange
                        )
                ),
                json = mapOf("value" to value)
        )
        Logger.log.debug("Request result ${responce.statusCode}")
        if (responce.statusCode == 200) {
            Logger.log.info("Pooling result success sent to ${url}")
        } else {
            Logger.log.error("Error while sending pooling result to ${url}")
        }
    }
}