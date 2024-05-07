package com.hzwl.iot.module.system.dal.entity.dict

import com.hzwl.framework.mybatis.core.entity.BaseEntity
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table

@Table("system_dict_data", comment = "字典数据表")
data class DictData(

    @Id(keyType = KeyType.Auto)
    var id: Long,
    var sort: Long,
    var label: String,
    var value: String,
    var dictType: String? = null,
    var status: Int,
    var colorType: String? = null,
    var cssClass: String? = null,
    var remark: String? = null,
) : BaseEntity()
