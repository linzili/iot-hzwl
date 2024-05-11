package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategorySaveReqVO
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.mybatisflex.core.service.IService

interface ProductCategoryService : IService<ProductCategory> {

    /**
     * 创建产品分类
     *
     * @param createReqVO 产品分类信息
     * @return 产品分类编号
     */
    fun createProductCategory(createReqVO: ProductCategorySaveReqVO): Long

    /**
     * 修改产品分类
     *
     * @param updateReqVO 产品分类信息
     * @return 是否成功
     */
    fun updateProductCategory(updateReqVO: ProductCategorySaveReqVO): Boolean
}
