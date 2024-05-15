package com.hzwl.iot.module.device.dal.entity.product

import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.hzwl.iot.module.device.dal.mapper.product.ProductCategoryMapper
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Table
import com.mybatisflex.kotlin.extensions.db.mapper

@Table("device_product_category", comment = "产品分类信息表")
data class ProductCategory(

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
     * 子分类
     */
    @Column(ignore = true)
    var children: List<ProductCategory>? = null,
) : TenantEntity<Long>() {
    companion object : ProductCategoryMapper by mapper()
}
