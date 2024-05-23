package com.hzwl.iot.tools.network.debugger.core.server

import com.hzwl.iot.tools.network.debugger.core.Event

interface Server {
    fun start()
    fun close()
    fun sendMessage(event: Event)
}
