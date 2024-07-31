package com.hzwl.iot.module.system.dal.mapper.dept

import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.db.filterOne
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface DeptMapper : BaseMapper<Dept> {
    fun selectByParentIdAndName(parentId: Long?, name: String): Dept? =
        filterOne {
            (Dept::parentId eq parentId).and(
                Dept::name eq name
            )
        }
}
