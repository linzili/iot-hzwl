package com.hzwl.iot.framework.mybatis.extensions

import com.mybatisflex.core.query.QueryCondition
import com.mybatisflex.kotlin.extensions.kproperty.column
import kotlin.reflect.KProperty

infix fun KProperty<String>.like(other: Any?): QueryCondition =
    other?.let { column.like(it) } ?: QueryCondition.createEmpty();
