package com.hzwl.iot.module.tools.websocket

import cn.hutool.extra.spring.SpringUtil
import cn.hutool.json.JSONUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.hzwl.iot.common.utils.NetworkHelper
import com.hzwl.iot.module.tools.properties.ToolsProperties
import com.hzwl.iot.tools.network.debugger.core.ActionEnum.*
import com.hzwl.iot.tools.network.debugger.core.Event
import com.hzwl.iot.tools.network.debugger.core.manager.ServerManager
import jakarta.websocket.OnClose
import jakarta.websocket.OnMessage
import jakarta.websocket.Session
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Component

@ServerEndpoint("/network-debugger-tool")
@Component
class NetworkDebuggerEndpoint {

    private val serverIdMap = mutableMapOf<String, Int>()

    val serverManager: ServerManager = SpringUtil.getBean(ServerManager::class.java)

    val toolsProperties: ToolsProperties = SpringUtil.getBean(ToolsProperties::class.java)

    @OnMessage
    fun onMessage(session: Session, message: String) {
        val event = try {
            ObjectMapper().readValue(message, Event::class.java)
        } catch (_: RuntimeException) {
            handleError(session, "发送消息格式有误")
            return
        }
        when (event.action) {
            NEW -> {
                val type = event.type
                val port = NetworkHelper.getRandomPort()
                serverIdMap[session.id]?.let {
                    handleError(session, "一个连接只能开启一个服务")
                }
                try {
                    serverManager.createServer(type!!, port) { handleEvent(it, session) }
                    serverIdMap[session.id] = port
                } catch (e: Exception) {
                    session.close()
                }
            }

            SEND -> {
                serverIdMap[session.id]?.let { id ->
                    serverManager.sendMessage(id, event)
                }
            }

            else -> {}
        }
    }

    private fun handleEvent(event: Event, session: Session) {
        val obj = JSONUtil.createObj().set("action", event.action!!.value)
        session.basicRemote?.sendText(
            when (event.action) {
                CONNECTED -> obj
                    .set("addr", event.addr)
                    .set("client", event.client)
                    .toString()

                DATA -> obj
                    .set("data", event.data)
                    .set("hex", event.hex)
                    .set("client", event.client)
                    .toString()

                DISCONNECT -> obj
                    .set("client", event.client)
                    .toString()

                PORT -> obj
                    .set("port", event.port)
                    .set("host",toolsProperties.ip)
                    .toString()

                else -> ""
            }
        )
    }

    fun handleError(session: Session, msg: String) {
        session.basicRemote?.sendText(
            JSONUtil.createObj()
                .set("action", "error")
                .set("data", msg)
                .toString()
        )
    }
    @OnClose
    fun onClose(session: Session) {
        serverIdMap[session.id]?.let { serverManager.closeServer(it) }
        serverIdMap.remove(session.id)
    }
}
