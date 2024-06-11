package com.hzwl.iot.tools.network.debugger.core.server

import com.hzwl.iot.common.extensions.toHexString
import com.hzwl.iot.tools.network.debugger.core.ActionEnum
import com.hzwl.iot.tools.network.debugger.core.Event
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class TcpServer(
    private val port: Int,
    private val eventCallback: (Event) -> Unit
) : Server {

    private val clientIdCounter = AtomicInteger(0)
    private val clients = ConcurrentHashMap<String, Socket>()
    private lateinit var serverSocket: ServerSocket
    private val isRunning = AtomicBoolean(false)
    private var serverThread: Thread? = null

    override fun start() {
        serverSocket = ServerSocket(port)
        isRunning.set(true)
        serverThread = thread {
            try {
                while (isRunning.get()) {
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
            } catch (e: SocketException) {
                if (isRunning.get()) {
                    e.printStackTrace()
                }
            }

        }
    }

    override fun close() {
        isRunning.set(false)
        serverThread?.interrupt()
        clients.values.forEach {
            it.close()
        }
        if (!serverSocket.isClosed) serverSocket.close()
    }

    override fun sendMessage(event: Event) {
        clients[event.client]?.let {
            val data = convertMessage(event)
            it.getOutputStream().write(data)
            it.getOutputStream().flush()
        }
    }

    private fun handleClient(clientId: String) {
        thread {
            clients[clientId]?.let {
                val inputStream = it.getInputStream()
                val buffer = ByteArray(1024)
                var bytesRead: Int

                try {
                    while (!it.isClosed) {
                        bytesRead = inputStream.read(buffer)
                        if (bytesRead == -1) break
                        if (bytesRead > 0) {
                            val message = buffer.sliceArray(0 until bytesRead).toHexString()
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

    private fun generateClientId(): String {
        val timestamp = Instant.now().epochSecond
        val counter = clientIdCounter.incrementAndGet()
        return "$timestamp-$counter"
    }
}
