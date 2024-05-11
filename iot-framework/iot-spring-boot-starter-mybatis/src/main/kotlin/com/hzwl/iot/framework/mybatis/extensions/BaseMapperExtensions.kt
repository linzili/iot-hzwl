package com.hzwl.iot.framework.mybatis.extensions

import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.pojo.PageResult
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.core.query.QueryCondition
import com.mybatisflex.kotlin.scope.QueryScope
import com.mybatisflex.kotlin.scope.UpdateScope
import com.mybatisflex.kotlin.scope.queryScope
import kotlin.reflect.KProperty
/**
 * 自定义扩展baseMapper
 * @author lin
 */

inline fun <reified R : Any> BaseMapper<*>.selectOneByQueryAs(
    init: QueryScope.() -> Unit
): R? {
    return selectOneByQueryAs(queryScope(init = init), R::class.java)
}

inline fun <reified R : Any> BaseMapper<*>.selectListByQueryAs(
    init: QueryScope.() -> Unit
): List<R> {
    return selectListByQueryAs(queryScope(init = init), R::class.java)
}


/**
 * 分页查询
 * @param pageParam 分页参数
 * @param init 查询作用域初始化函数
 */
inline fun <reified T : Any> BaseMapper<T>.paginate(
    pageParam: PageParam,
    init: QueryScope.() -> Unit
): PageResult<T> {
    val page = paginate(pageParam.page!!, pageParam.size!!, queryScope(init = init))
    return PageResult(page.records, page.totalRow)
}

/**
 * 分页查询
 * @param pageParam 分页参数
 * @param condition 查询条件函数
 */
inline fun <reified T : Any> BaseMapper<T>.paginateWith(
    pageParam: PageParam,
    condition: () -> QueryCondition
): PageResult<T> {
    return paginate(pageParam) { where(condition()) }
}


/**
 * 分页查询
 * @param R 接收数据类型
 * @param pageParam 分页参数
 * @param init 查询作用域初始化函数
 */
inline fun <reified R : Any> BaseMapper<*>.paginateAs(
    pageParam: PageParam,
    init: QueryScope.() -> Unit
): PageResult<R> {
    val page = paginateAs(pageParam.page!!, pageParam.size!!, queryScope(init = init), R::class.java)
    return PageResult(page.records, page.totalRow)
}

/**
 * 分页查询
 * @param R 接收数据类型
 * @param pageParam 分页参数
 * @param columns 查询字段
 * @param condition 查询条件函数
 */
inline fun <reified R : Any> BaseMapper<*>.paginateWithAs(
    pageParam: PageParam,
    vararg columns: KProperty<*> = emptyArray(),
    condition: () -> QueryCondition
): PageResult<R> {
    return paginateAs<R>(pageParam) {
        select(*columns).and(condition())
    }
}


inline fun <reified T : Any> BaseMapper<T>.update(scope: UpdateScope<T>.() -> Unit): Int =
    com.mybatisflex.kotlin.extensions.db.update<T>(scope)
