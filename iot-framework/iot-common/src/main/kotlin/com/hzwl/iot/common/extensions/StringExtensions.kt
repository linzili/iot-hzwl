package com.hzwl.iot.common.extensions


// 扩展函数：将字符串转换为十六进制字符串
fun String.toHexString(): String {
    return this.toByteArray().joinToString("") { "%02x".format(it) }
}


// 扩展函数：将十六进制字符串转换为普通字符串
fun String.fromHexString(): String {
    // 使用正则表达式匹配每两个字符
    val hexBytes = this.chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
    return String(hexBytes)
}


fun String.hexStringToByteArray(): ByteArray {
    val len = this.length
    require(len % 2 == 0) { "Hex string must have an even length" }

    return ByteArray(len / 2) { i ->
        val index = i * 2
        ((this[index].hexToInt() shl 4) + this[index + 1].hexToInt()).toByte()
    }
}
