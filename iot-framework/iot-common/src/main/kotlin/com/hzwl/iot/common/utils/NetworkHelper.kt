package com.hzwl.iot.common.utils

import java.net.ServerSocket

object NetworkHelper {
    fun getRandomPort(): Int = ServerSocket(0).use { it.localPort }
}
