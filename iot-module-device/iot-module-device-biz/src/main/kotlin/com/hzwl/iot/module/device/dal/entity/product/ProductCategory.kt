package com.hzwl.iot.module.device.dal.entity.product

import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.hzwl.iot.module.device.dal.mapper.product.ProductCategoryMapper
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper

@Table("device_product_category", comment = "产品分类信息表")
data class ProductCategory(

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long?,
    /**
     * 父分类id
     */
    val parentId: Long?,

    /**
     * 分类名称
     */
    val name: String,

    /**
     * 排序
     */
    val sort: Int,

    /**
     * 备注
     */
    val remark: String?,

    /**
     * 租户id
     */
    val tenantId: Long,
) : BaseEntity() {
    companion object : ProductCategoryMapper by mapper()
}
