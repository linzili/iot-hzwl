package com.hzwl.iot.module.device.dal.mapper.product

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper : BaseMapper<Product> {
    fun selectPage(pageReqVO: ProductPageReqVO): PageResult<ProductRespVO> =
        paginateAs<ProductRespVO>(pageReqVO) {
            andAll(
                Product::name like pageReqVO.name,
                Product::deviceType eq pageReqVO.deviceType,
                Product::publish eq pageReqVO.publish,
                Product::categoryId eq pageReqVO.categoryId,
                Product::deviceType eq pageReqVO.deviceType,
                Product::createTime between pageReqVO.createTime
            )
            orderBy(+Product::id)
        }

}
