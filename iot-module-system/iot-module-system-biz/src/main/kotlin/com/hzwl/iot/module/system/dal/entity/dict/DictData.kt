package com.hzwl.iot.module.system.dal.entity.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators

@Table("system_dict_data", comment = "字典数据表")
data class DictData(

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long?,

    val sort: Int,

    val label: String,

    val value: String,

    val dictType: String,

    val status: CommonStatusEnum? = null,

    val colorType: String? = null,

    val cssClass: String? = null,

    val remark: String? = null,
) : BaseEntity()
