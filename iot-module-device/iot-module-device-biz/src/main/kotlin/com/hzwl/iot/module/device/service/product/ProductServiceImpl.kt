package com.hzwl.iot.module.device.service.product

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.selectListByQueryAs
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSimpleRespVO
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.dal.mapper.product.ProductMapper
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.db.update
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    val productCategoryService: ProductCategoryService
) : ServiceImpl<ProductMapper, Product>(), ProductService {
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

        validateProductNotPublish(dbProduct)

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

    /**
     * 查询精简产品列表
     *
     * @return
     */
    override fun getSimpleProductList(): List<ProductSimpleRespVO> =
        mapper.selectListByQueryAs<ProductSimpleRespVO> {
            where(Product::publish eq CommonStatusEnum.ENABLE)
            orderBy(Product::categoryId)
            orderBy(Product::id)
        }

    /**
     * 发布产品
     *
     * @param id 产品id
     * @return 是否成功
     */
    override fun publishProduct(id: Long): Boolean {

        val product = validateProductExists(id)

        validateProductNotPublish(product)

        // todo: 校验产品是否完成配置
        product.publish = CommonStatusEnum.ENABLE

        return updateById(product)
    }

    /**
     * 取消发布产品
     *
     * @param id 产品id
     * @return 是否成功
     */
    override fun unpublishProduct(id: Long): Boolean {
        val product = validateProductExists(id)

        validateProductPublish(product)

        product.publish = CommonStatusEnum.DISABLE

        return updateById(product)
    }

    /**
     * 更新产品配置
     *
     * @param id 产品id
     * @param config 配置
     * @return 是否成功
     */
    override fun updateProductConfig(id: Long, config: Map<String, Any>): Boolean {
        validateProductExists(id)

        return update {
            Product::configuration set config
            where(Product::id eq id)
        } == 1
    }

    /**
     * 校验产品是否存在
     *
     * @param productId
     * @return 产品信息
     */
    override fun validateProductExistAndPublish(productId: Long): Product {

        val product = getById(productId) ?: throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)
        if (product.publish === CommonStatusEnum.DISABLE) {
            throw exception(ErrorCodeConstants.PRODUCT_PUBLISH_DISABLE)
        }
        return product
    }


    /**
     * 校验产品未发布
     *
     * @param product
     */
    private fun validateProductNotPublish(product: Product) {
        if (product.publish === CommonStatusEnum.ENABLE) {
            throw exception(ErrorCodeConstants.PRODUCT_PUBLISH_ENABLE)
        }
    }

    /**
     * 校验产品已发布
     *
     * @param product
     */
    private fun validateProductPublish(product: Product) {
        if (product.publish === CommonStatusEnum.DISABLE) {
            throw exception(ErrorCodeConstants.PRODUCT_PUBLISH_DISABLE)
        }
    }

    private fun validateProductExists(id: Long?): Product =
        if (id == null) {
            throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)
        } else getById(id) ?: throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)


    private fun validateProductCategoryExists(categoryId: Long): ProductCategory =
        productCategoryService.getById(categoryId)
            ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)


    private fun validateProductNameUnique(name: String) {
        if (count(Product::name.eq(name)) > 0) {
            throw exception(ErrorCodeConstants.PRODUCT_NAME_DUPLICATE)
        }
    }

}
