package com.hzwl.iot.tools.network.debugger.core.server

import com.hzwl.iot.common.extensions.toHexString
import com.hzwl.iot.tools.network.debugger.core.ActionEnum
import com.hzwl.iot.tools.network.debugger.core.Event
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class TcpServer(
    private val port: Int,
    private val eventCallback: (Event) -> Unit
) : Server {
    private val clientIdCounter = AtomicInteger(0)
    private val clients = ConcurrentHashMap<String, Socket>()
    private lateinit var serverSocket: ServerSocket

    override fun start() {
        serverSocket = ServerSocket(port)
        thread {
            while (true) {
                val clientSocket = serverSocket.accept()
                val clientId = generateClientId()
                clients[clientId] = clientSocket
                eventCallback(
                    Event(
                        ActionEnum.CONNECTED,
                        client = clientId,
                        addr = "${clientSocket.inetAddress.hostAddress}:${clientSocket.port}"
                    )
                )
                handleClient(clientId)
            }
        }
    }

    override fun close() {
        if (!serverSocket.isClosed) serverSocket.close()
        clients.values.forEach {
            it.close()
        }
    }

    override fun sendMessage(event: Event) {
        clients[event.client]?.let {
            val data = event.data?.let { msg ->
                if (event.hex == true) {
                    msg.toByteArray()
                } else {
                    msg.toHexString().toByteArray()
                }
            } ?: ByteArray(0)
            it.getOutputStream().write(data)
            it.getOutputStream().flush()
        }
    }

    private fun handleClient(clientId: String) {
        thread {
            clients[clientId]?.let {
                val reader = it.getInputStream().bufferedReader()
                val buffer = CharArray(1024)
                var bytesRead: Int

                try {
                    while (it.isConnected) {
                        bytesRead = reader.read(buffer)
                        if (bytesRead == -1) break
                        if (bytesRead > 0) {
                            val message = String(buffer, 0, bytesRead).toHexString()
                            eventCallback(Event(ActionEnum.DATA, data = message, client = clientId))
                        }
                    }
                } finally {
                    clients.remove(clientId)
                    it.close()
                    eventCallback(Event(ActionEnum.DISCONNECT, client = clientId))
                }

            }
        }
    }

    private fun generateClientId(): String = clientIdCounter.incrementAndGet().toString()
}
