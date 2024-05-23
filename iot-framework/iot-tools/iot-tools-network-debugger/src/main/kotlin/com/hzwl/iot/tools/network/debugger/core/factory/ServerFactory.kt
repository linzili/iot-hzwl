package com.hzwl.iot.tools.network.debugger.core.factory

import com.hzwl.iot.tools.network.debugger.core.Event
import com.hzwl.iot.tools.network.debugger.core.server.Server
import com.hzwl.iot.tools.network.debugger.core.server.TcpServer
import com.hzwl.iot.tools.network.debugger.core.server.UdpServer
import org.springframework.stereotype.Component

@Component
class ServerFactory {
    fun createServer(type: String, port: Int, eventCallback: (Event) -> Unit): Server {
        return when (type) {
            "tcp" -> TcpServer(port, eventCallback)
            "udp" -> UdpServer(port, eventCallback)
            else -> throw IllegalArgumentException("Invalid server type: $type")
        }
    }
}
