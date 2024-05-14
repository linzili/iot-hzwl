package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.dal.mapper.product.ProductMapper
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl : ServiceImpl<ProductMapper, Product>(), ProductService {
    /**
     * 创建产品
     *
     * @param createReqVo 产品信息
     * @return
     */
    override fun createProduct(createReqVo: ProductSaveReqVO): Long {
        validateProductNameUnique(createReqVo.name!!)

        val category = validateProductCategoryExists(createReqVo.categoryId)

        val product = convert(createReqVo, Product::class.java)

        product.id = null
        product.publish = CommonStatusEnum.DISABLE
        category?.let {
            product.categoryName = product.name
        }
        save(product)
        return product.id!!
    }

    private fun validateProductCategoryExists(categoryId: Long?): ProductCategory? {
        categoryId?.let {
            return ProductCategory.selectOneById(categoryId)
                ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)

        } ?: return null
    }

    private fun validateProductNameUnique(name: String) {
        if (count(Product::name.eq(name)) > 0) {
            throw exception(ErrorCodeConstants.PRODUCT_NAME_DUPLICATE)
        }
    }


}
