package com.hzwl.iot.common.extensions

import cn.hutool.extra.spring.SpringUtil
import io.github.linpeilie.Converter


val <T : Any> T.convert: Converter
    get() = SpringUtil.getBean(Converter::class.java)
