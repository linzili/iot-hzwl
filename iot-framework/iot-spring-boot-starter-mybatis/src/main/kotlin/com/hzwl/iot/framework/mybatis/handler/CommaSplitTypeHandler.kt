package com.hzwl.iot.framework.mybatis.handler


import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

abstract class CommaSplitTypeHandler<T> : BaseTypeHandler<List<T>>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: List<T>?, jdbcType: JdbcType?) {
        if (parameter != null) {
            ps.setString(i, parameter.joinToString(","))
        } else {
            jdbcType?.let { ps.setNull(i, it.TYPE_CODE) }
        }
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): List<T>? {
        return splitByComma(rs.getString(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): List<T>? {
        return splitByComma(rs.getString(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): List<T>? {
        return splitByComma(cs.getString(columnIndex))
    }

    abstract fun convertToType(value: String): T

    private fun splitByComma(string: String?): List<T>? {
        return string?.takeIf { it.isNotEmpty() }?.split(",")?.map { convertToType(it) }
    }
}
