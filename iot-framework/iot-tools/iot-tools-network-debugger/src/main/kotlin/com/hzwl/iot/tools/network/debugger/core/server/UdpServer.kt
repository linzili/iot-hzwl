package com.hzwl.iot.tools.network.debugger.core.server

import com.hzwl.iot.common.extensions.toHexString
import com.hzwl.iot.common.utils.BidirectionalMap
import com.hzwl.iot.tools.network.debugger.core.ActionEnum
import com.hzwl.iot.tools.network.debugger.core.Event
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.net.SocketException
import java.time.Instant
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class UdpServer(
    private val port: Int,
    private val eventCallback: (Event) -> Unit
) : Server {
    private val clientIdCounter = AtomicInteger(0)
    private val clients = BidirectionalMap<String, String>()
    private lateinit var socket: DatagramSocket
    private val isRunning = AtomicBoolean(false)
    private var serverThread: Thread? = null

    override fun start() {
        socket = DatagramSocket(port)
        isRunning.set(true)
        serverThread = thread {
            val buffer = ByteArray(1024)
            while (isRunning.get()) {
                try {
                    val packet = DatagramPacket(buffer, buffer.size)
                    socket.receive(packet)
                    handleMessage(packet)
                } catch (e: SocketException) {
                    if (!isRunning.get()) {
                        break
                    } else {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun close() {
        isRunning.set(false)
        serverThread?.interrupt()
        socket.close()
    }

    override fun sendMessage(event: Event) {
        val clientAddress = event.client?.let { clients.getByB(it) }
        if (clientAddress != null) {
            val addressParts = clientAddress.split(":")
            if (addressParts.size == 2) {
                val inetAddress = addressParts[0]
                val port = addressParts[1].toInt()
                val data = convertMessage(event)
                val packet = DatagramPacket(data, data.size, InetSocketAddress(inetAddress, port))
                socket.send(packet)
            }
        }
    }

    private fun generateClientId(): String {
        val timestamp = Instant.now().epochSecond
        val counter = clientIdCounter.incrementAndGet()
        return "$timestamp-$counter"
    }

    private fun handleMessage(packet: DatagramPacket) {
        val socketAddress = packet.socketAddress.toString().removePrefix("/")
        handleNewConnection(socketAddress)
        val data = packet.data.sliceArray(0 until packet.length).toHexString()
        eventCallback(
            Event(
                ActionEnum.DATA,
                client = clients.getByA(socketAddress),
                data = data,
                hex = true
            )
        )
    }

    private fun handleNewConnection(socketAddress: String) {
        if (clients.getByA(socketAddress) == null) {
            val clientId = generateClientId()
            clients.put(socketAddress, clientId)
            eventCallback(
                Event(
                    action = ActionEnum.CONNECTED,
                    client = clientId,
                    addr = socketAddress
                )
            )
        }
    }
}
