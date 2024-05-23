package com.hzwl.iot.common.extensions


fun String.toHexString(): String {
    return this.toByteArray().joinToString("") { "%02x".format(it) }
}
