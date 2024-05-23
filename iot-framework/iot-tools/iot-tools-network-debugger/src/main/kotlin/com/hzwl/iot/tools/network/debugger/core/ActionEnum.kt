package com.hzwl.iot.tools.network.debugger.core

import com.fasterxml.jackson.annotation.JsonValue

enum class ActionEnum(@JsonValue val value: String) {

    NEW("new"),
    SEND("send"),
    CONNECTED("connected"),
    DATA("data"),
    DISCONNECT("disconnect"),
    PORT("port");

}
