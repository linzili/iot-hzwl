package com.hzwl.iot.module.system.dal.entity.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.hzwl.iot.module.system.dal.enums.dict.ColorType
import com.hzwl.iot.module.system.dal.mapper.dict.DictDataMapper
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper


@Table("system_dict_data", comment = "字典数据表")
data class DictData(

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long? = null,
    /**
     * 排序
     */
    val sort: Int,

    /**
     * 字典标签
     */
    val label: String,

    /**
     * 字典键值
     */
    val value: Int,

    /**
     * 字典类型
     */
    val dictType: String,

    /**
     * 状态（0正常 1停用）
     */
    val status: CommonStatusEnum,

    /**
     * 颜色类型, default、primary、success、warning、danger、info
     */
    val colorType: ColorType? = null,

    /**
     * css 样式
     */
    val cssClass: String? = null,

    /**
     * 备注
     */
    val remark: String? = null,
) : BaseEntity() {
    companion object : DictDataMapper by mapper()
}
