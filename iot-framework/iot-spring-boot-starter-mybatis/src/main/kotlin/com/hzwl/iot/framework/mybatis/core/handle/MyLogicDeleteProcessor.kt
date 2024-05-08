package com.hzwl.iot.framework.mybatis.core.handle

import com.mybatisflex.core.constant.SqlConsts.EQUALS
import com.mybatisflex.core.dialect.IDialect
import com.mybatisflex.core.logicdelete.impl.BooleanLogicDeleteProcessor
import com.mybatisflex.core.table.TableInfo


/**
 * 自定义逻辑删除处理器
 * @author lin
 */
class MyLogicDeleteProcessor : BooleanLogicDeleteProcessor() {
    override fun buildLogicDeletedSet(logicColumn: String, tableInfo: TableInfo, dialect: IDialect): String {
        var sql = dialect.wrap(logicColumn) + EQUALS + logicDeletedValue

        if (tableInfo.allColumns.contains("delete_time")) {
            sql += " ,${dialect.wrap("delete_time")}$EQUALS now()"
        }

        return sql
    }
}
