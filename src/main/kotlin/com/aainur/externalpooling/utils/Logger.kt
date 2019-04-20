package com.aainur.externalpooling.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Logger {
    companion object {
        val log by logger()
    }
}

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(this::class.java.name) }
}