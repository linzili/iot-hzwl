package com.hzwl.iot.common.extensions


fun ByteArray.toHexString(): String {
    return this.joinToString("") {
        "%02X".format(it)
    }
}
