package com.hzwl.iot.module.system.dal.entity.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators

@Table("system_dict_type")
data class DictType(

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long? = null,

    /**
     * 字典名称
     */
    val name: String,

    /**
     * 字典类型
     */
    val type: String,

    /**
     * 状态
     * @see CommonStatusEnum
     */
    val status: CommonStatusEnum,

    /**
     * 备注
     */
    val remark: String? = null,
) : BaseEntity()
