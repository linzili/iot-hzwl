package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.mybatisflex.core.service.IService

interface ProductService : IService<Product> {

    /**
     * 创建产品
     *
     * @param createReqVo 产品信息
     * @return 产品id
     */
    fun createProduct(createReqVo: ProductSaveReqVO): Long

    /**
     * 修改产品
     *
     * @param updateReqVo 产品信息
     * @return 是否成功
     */
    fun updateProduct(updateReqVo: ProductSaveReqVO): Boolean

    /**
     * 删除产品
     *
     * @param id 产品id
     * @return 是否成功
     */
    fun deleteProduct(id: Long): Boolean

    /**
     * 获得产品分页列表
     *
     * @param pageReqVO 分页参数
     * @return 产品分页列表
     */
    fun getProductPage(pageReqVO: ProductPageReqVO): PageResult<ProductRespVO>

}
