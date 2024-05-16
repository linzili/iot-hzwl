package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.mybatisflex.core.service.IService

interface ProductService : IService<Product> {

    /**
     * 创建产品
     *
     * @param createReqVo 产品信息
     * @return
     */
    fun createProduct(createReqVo: ProductSaveReqVO): Long

}