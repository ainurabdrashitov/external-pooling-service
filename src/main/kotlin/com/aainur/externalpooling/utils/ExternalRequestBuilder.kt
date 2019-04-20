package com.aainur.externalpooling.utils

import com.aainur.externalpooling.models.Exchange
import org.springframework.stereotype.Component

@Component
class ExternalRequestBuilder {

    private val marketUrls = mapOf(
            Exchange.BITTREX to "/getmarketsummary"
    )

    fun buildUrl(service: String): String {
        return String.format("%s%s", Exchange.valueOf(service).url, marketUrls[Exchange.BITTREX])
    }
}
