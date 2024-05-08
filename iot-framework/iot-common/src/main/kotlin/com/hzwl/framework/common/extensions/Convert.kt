package com.hzwl.framework.common.extensions

import io.github.linpeilie.Converter
import org.springframework.beans.factory.annotation.Autowired

@Autowired
lateinit var converter: Converter

val <T : Any> T.convert: Converter
    get() = converter
