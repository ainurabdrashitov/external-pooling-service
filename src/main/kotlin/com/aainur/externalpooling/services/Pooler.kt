package com.aainur.externalpooling.services

import com.aainur.externalpooling.utils.Logger
import khttp.responses.Response
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Pooler(private val resultSender: ResultSender) : CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun cancel() {
        job.cancel()
    }

    fun run(url: String, currency: String) = launch {
        while (true) {
            Logger.log.debug("Trying request to ${url}")
            val responce = khttp.get(
                    url = url,
                    params = mapOf("market" to String.format("usdt-%s", currency)))
            Logger.log.debug("Request result ${responce.statusCode}")
            if (responce.statusCode == 200) {
                process(currency, responce)
            } else {
                handleError(responce)
            }
            delay(1000)
        }
    }

    private fun process(currency: String, response: Response) {
        if (response.jsonObject.getBoolean("success")) {
            val value = response.jsonObject.getJSONArray("result").getJSONObject(0).getDouble("Last")
            resultSender.sendResult(currency, "bittrex", value.toString())
        }
    }

    private fun handleError(response: Response) {}
}
