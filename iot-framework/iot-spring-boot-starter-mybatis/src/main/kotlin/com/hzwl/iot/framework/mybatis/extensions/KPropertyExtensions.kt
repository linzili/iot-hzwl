package com.hzwl.iot.framework.mybatis.extensions

import com.mybatisflex.core.query.QueryCondition
import com.mybatisflex.kotlin.extensions.kproperty.column
import kotlin.reflect.KProperty

infix fun KProperty<String?>.like(other: Any?): QueryCondition =
    other?.let { column.like(it) } ?: QueryCondition.createEmpty()


/**
 * between
 *
 * @param other 一个可空的数组，包含了用于比较的边界值。如果数组大小为2或更大，查询条件将基于最小和最大的范围；
 *              如果大小为1，则条件为大于等于数组中的唯一元素；如果为空，返回一个空的查询条件。
 * @return 返回一个 [QueryCondition] 对象，表示属性值与给定边界的关系。
 */
infix fun <T : Any, V> KProperty<V>.between(other: Array<T>?): QueryCondition =
    other?.let {
        if (it.size >= 2) {
            it.sort()
            column.between(it.first(), it.last())
        } else if (it.size == 1) {
            column.ge(it[0])
        } else {
            QueryCondition.createEmpty()
        }
    } ?: QueryCondition.createEmpty()
