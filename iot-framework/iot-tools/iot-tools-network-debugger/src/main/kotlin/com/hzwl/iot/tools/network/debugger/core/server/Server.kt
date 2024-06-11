package com.hzwl.iot.tools.network.debugger.core.server

import com.hzwl.iot.common.extensions.hexStringToByteArray
import com.hzwl.iot.tools.network.debugger.core.Event

interface Server {
    fun start()
    fun close()
    fun sendMessage(event: Event)

    fun convertMessage(event: Event): ByteArray {
        return if (event.hex == true) {
            event.data?.hexStringToByteArray()
        } else {
            event.data?.toByteArray()
        } ?: ByteArray(0)
    }
}
