package com.hzwl.iot.common.extensions

import cn.hutool.extra.spring.SpringUtil
import io.github.linpeilie.Converter
import io.github.linpeilie.CycleAvoidingMappingContext


val converter: Converter
    get() = SpringUtil.getBean(Converter::class.java)


fun <S, R> convert(source: S, targetType: Class<R>): R {
    return converter.convert(source, targetType)
}

fun <S, R> convert(source: S, target: R): R {
    return converter.convert(source, target)
}

fun <S, R> convert(source: List<S>, targetType: Class<R>): List<R> {
    return converter.convert(source, targetType)
}

fun <S, R> convert(source: S, targetType: Class<R>, context: CycleAvoidingMappingContext): R {
    return converter.convert(source, targetType, context)
}

fun <S, R> convert(source: List<S>, targetType: Class<R>, context: CycleAvoidingMappingContext): List<R> {
    return converter.convert(source, targetType, context)
}

fun <T> convert(map: Map<String, Any>, targetType: Class<T>): T {
    return converter.convert(map, targetType)
}
