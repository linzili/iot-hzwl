package com.hzwl.iot.tools.network.debugger.core

class Event(
    val action: ActionEnum? = null,
    val client: String? = null,
    val data: String? = null,
    val addr: String? = null,
    val hex: Boolean? = null,
    val port: Int? = null,
    val type: String? = null,
)
