package com.hzwl.iot.common.extensions

fun Char.hexToInt(): Int {
    return this.toString().toInt(16)
}
