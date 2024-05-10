package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategorySaveReqVO
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.dal.mapper.product.ProductCategoryMapper
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class ProductCategoryServiceImpl : ServiceImpl<ProductCategoryMapper, ProductCategory>(), ProductCategoryService {
    /**
     * 创建产品分类
     *
     * @param createReqVO 产品分类信息
     * @return 产品分类编号
     */
    override fun createProductCategory(createReqVO: ProductCategorySaveReqVO): Long {
        createReqVO.parentId?.let {
            validateParentProductCategoryExists(createReqVO.parentId)
        }

        validateProductCategoryNameUnique(null, createReqVO.name!!)

        val productCategory = convert(createReqVO, ProductCategory::class.java)
        productCategory.id = null
        save(productCategory)
        return productCategory.id!!
    }

    /**
     * 校验产品分类名称的唯一性
     *
     * @param id
     * @param name
     */
    private fun validateProductCategoryNameUnique(id: Long?, name: String) {
        val category = mapper.selectOneByCondition(ProductCategory::name eq name) ?: return

        if (category.id != id) {
            throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NAME_DUPLICATE)
        }
    }

    /**
     * 校验产品分类是否存在
     *
     * @param id 产品分类编号
     */
    private fun validateProductCategoryExists(id: Long?) {
        id?.let {
            getById(it) ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)
        } ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)
    }

    /**
     * 校验父级产品分类是否存在
     *
     * @param parentId 父分类编号
     */
    private fun validateParentProductCategoryExists(parentId: Long) {
        getById(parentId) ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_PARENT_NOT_EXISTS)
    }
}
