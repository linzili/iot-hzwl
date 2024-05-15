package com.hzwl.iot.module.device.dal.entity.product

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.dal.mapper.product.ProductMapper
import com.hzwl.iot.module.device.enums.DeviceTypeEnum
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.handler.JacksonTypeHandler
import com.mybatisflex.kotlin.extensions.db.mapper
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.AutoMappers

@Table("device_product")
@AutoMappers(
    AutoMapper(target = ProductSaveReqVO::class)
)
data class Product(

    /**
     * 产品名称
     */
    val name: String,

    /**
     * 产品分类id
     */
    val categoryId: Long?,

    /**
     * 产品分类名称
     */
    var categoryName: String?,

    /**
     * 产品类型
     */
    val deviceType: DeviceTypeEnum,

    /**
     * 是否发布
     */
    var publish: CommonStatusEnum?,

    /**
     * 配置
     */
    @Column(typeHandler = JacksonTypeHandler::class)
    val configuration: Map<String, Any>?,

    /**
     * 描述
     */
    val description: String?,

    /**
     * 备注
     */
    val remark: String?,

) : TenantEntity<Long>() {
    companion object : ProductMapper by mapper()
}
