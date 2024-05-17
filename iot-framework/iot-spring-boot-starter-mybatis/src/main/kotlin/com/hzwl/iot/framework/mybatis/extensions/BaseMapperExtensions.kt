package com.hzwl.iot.framework.mybatis.extensions

import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.pojo.PageResult
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.core.query.QueryCondition
import com.mybatisflex.core.query.QueryOrderBy
import com.mybatisflex.kotlin.extensions.condition.allAnd
import com.mybatisflex.kotlin.scope.QueryScope
import com.mybatisflex.kotlin.scope.UpdateScope
import com.mybatisflex.kotlin.scope.queryScope
import kotlin.reflect.KProperty


/**
 * 根据查询条件来查询 1 条数据。
 * @param R 接收数据类型
 * @param init 查询作用域
 * @return 实体类数据
 */
inline fun <reified R : Any> BaseMapper<*>.selectOneByQueryAs(
    init: QueryScope.() -> Unit
): R? {
    return selectOneByQueryAs(queryScope(init = init), R::class.java)
}

/**
 * 根据查询条件查询数据列表，要求返回的数据为 asType。这种场景一般用在 left join 时，
 * 有多出了实体类本身的字段内容，可以转换为 dto、vo 等场景。
 *
 * @param R 接收数据类型
 * @param init 查询作用域
 * @return 数据列表
 */
inline fun <reified R : Any> BaseMapper<*>.selectListByQueryAs(
    init: QueryScope.() -> Unit,
): List<R> {
    return selectListByQueryAs(queryScope(init = init), R::class.java)
}

/**
 * 根据排序查询数据列表
 *
 * @param T
 * @param init 查询作用域
 * @return 数据列表
 */
inline fun <T> BaseMapper<T>.selectListByQuery(
    init: QueryScope.() -> Unit,
): List<T> {
    return selectListByQuery(queryScope(init = init))
}

/**
 * 根据排序查询数据列表
 *
 * @param T
 * @param condition 排序条件
 * @return 数据列表
 */
inline fun <T> BaseMapper<T>.selectListByOrderBy(
    condition: () -> QueryOrderBy
): List<T> {
    return selectListByQuery(queryScope(init = { orderBy(condition()) }))
}

/**
 * 根据条件查询数据列表
 *
 * @param T
 * @param condition 查询条件
 * @return 数据列表
 */
inline fun <T> BaseMapper<T>.selectListByCondition(
    condition: () -> QueryCondition
): List<T> {
    return selectListByQuery(queryScope(init = { and(condition()) }))
}

/**
 * 根据条件查询数据列表
 *
 * @param R 返回类型
 * @param condition 查询条件
 * @return 数据列表
 */
inline fun <reified R : Any> BaseMapper<*>.selectListByConditionAs(
    condition: () -> QueryCondition
): List<R> {
    return selectListByQueryAs<R> { and(condition()) }
}

/**
 * 根据条件查询数量
 *
 * @param condition
 * @receiver
 * @return
 */
inline fun BaseMapper<*>.selectCountByCondition(
    condition: () -> QueryCondition
): Long {
    return selectCountByQuery(queryScope(init = { and(condition()) }))
}

/**
 * 查询实体类及其 Relation 注解字段。
 * @param R 接收数据类型
 * @param init  查询作用域
 * @return 数据列表
 */
inline fun <reified R> BaseMapper<*>.selectListWithRelationsByQueryAs(
    init: QueryScope.() -> Unit
): List<R> = queryScope(init = init).let { this.selectListWithRelationsByQueryAs(it, R::class.java) }

/**
 * 查询实体类及其 Relation 注解字段。
 * @param R 接收数据类型
 * @param condition  查询条件
 * @return 数据列表
 */
inline fun <reified R> BaseMapper<*>.selectListWithRelationsByQueryAs(
    vararg condition: QueryCondition
): List<R> =
    queryScope(init = { where(allAnd(*condition)) }).let { this.selectListWithRelationsByQueryAs(it, R::class.java) }

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


/**
 * 更新数据
 * @param scope 更新作用域
 * @return 更新数量
 */
inline fun <reified T : Any> BaseMapper<T>.update(scope: UpdateScope<T>.() -> Unit): Int =
    com.mybatisflex.kotlin.extensions.db.update<T>(scope)

