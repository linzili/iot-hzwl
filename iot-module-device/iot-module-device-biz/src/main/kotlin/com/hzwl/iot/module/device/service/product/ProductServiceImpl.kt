package com.hzwl.iot.module.device.service.product

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
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

        val category = createReqVo.categoryId?.let { validateProductCategoryExists(it) }

        val product = convert(createReqVo, Product::class.java)

        product.id = null
        product.publish = CommonStatusEnum.DISABLE
        category?.let {
            product.categoryName = product.name
        }
        save(product)
        return product.id!!
    }

    /**
     * 修改产品
     *
     * @param updateReqVo 产品信息
     * @return 是否成功
     */
    override fun updateProduct(updateReqVo: ProductSaveReqVO): Boolean {
        val dbProduct = validateProductExists(updateReqVo.id)

        validateProductNameUnique(updateReqVo.name!!)

        val category = updateReqVo.categoryId?.let { validateProductCategoryExists(it) }

        val product = convert(updateReqVo, Product::class.java)

        product.publish = dbProduct.publish
        product.configuration = dbProduct.configuration

        if (category != null) product.categoryName = category.name else product.categoryName = null

        return updateById(product, false)
    }

    /**
     * 删除产品
     *
     * @param id 产品id
     * @return 是否成功
     */
    override fun deleteProduct(id: Long): Boolean {
        val dbProduct = validateProductExists(id)
        if (dbProduct.publish === CommonStatusEnum.ENABLE) {
            throw exception(ErrorCodeConstants.PRODUCT_PUBLISH_ENABLE)
        }
        publishEvent(dbProduct)
        return removeById(id)
    }

    /**
     * 获得产品分页列表
     *
     * @param pageReqVO 分页参数
     * @return 产品分页列表
     */
    override fun getProductPage(pageReqVO: ProductPageReqVO): PageResult<ProductRespVO> =
        mapper.selectPage(pageReqVO)


    private fun validateProductExists(id: Long?): Product =
        if (id == null) {
            throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)
        } else getById(id) ?: throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)


    private fun validateProductCategoryExists(categoryId: Long): ProductCategory =
        ProductCategory.selectOneById(categoryId)
            ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)


    private fun validateProductNameUnique(name: String) {
        if (count(Product::name.eq(name)) > 0) {
            throw exception(ErrorCodeConstants.PRODUCT_NAME_DUPLICATE)
        }
    }

}
