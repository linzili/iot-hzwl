package com.hzwl.iot.tools.network.debugger.core.manager

import com.hzwl.iot.tools.network.debugger.core.ActionEnum
import com.hzwl.iot.tools.network.debugger.core.Event
import com.hzwl.iot.tools.network.debugger.core.factory.ServerFactory
import com.hzwl.iot.tools.network.debugger.core.server.Server
import org.springframework.stereotype.Component

@Component
class ServerManager(
    private val serverFactory: ServerFactory
) {
    private val servers = mutableMapOf<Int, Server>()

    fun createServer(
        type: String,
        port: Int,
        eventCallback: (Event) -> Unit
    ) {
        val server = serverFactory.createServer(type, port, eventCallback)
        servers[port] = server
        server.start()
        eventCallback(Event(ActionEnum.PORT, port = port))
    }

    fun sendMessage(id: Int, event: Event) {
        servers[id]?.sendMessage(event)
    }

    fun closeServer(port: Int) {
        servers[port]?.close()
    }
}
