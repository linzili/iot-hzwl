package com.hzwl.iot.module.system.dal.entity.dict

import com.hzwl.framework.common.enums.CommonStatusEnum
import com.hzwl.framework.mybatis.core.entity.BaseEntity
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table

@Table("system_dict_data", comment = "字典数据表")
data class DictData(

    @Id(keyType = KeyType.Auto)
    var id: Long,

    var sort: Int,

    var label: String,

    var value: String,

    var dictType: String,

    var status: CommonStatusEnum,

    var colorType: String? = null,

    var cssClass: String? = null,

    var remark: String? = null,

) : BaseEntity()
