package com.hzwl.iot.module.device.service.product

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.framework.mybatis.extensions.selectListByOrderBy
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategorySaveReqVO
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategoryTreeRespVO
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.dal.mapper.product.ProductCategoryMapper
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
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

        validateParentProductCategoryExists(createReqVO.parentId)

        validateProductCategoryNameUnique(null, createReqVO.name!!)

        val productCategory = convert(createReqVO, ProductCategory::class.java)
        productCategory.id = null
        save(productCategory)
        return productCategory.id!!
    }

    /**
     * 修改产品分类
     *
     * @param updateReqVO 产品分类信息
     * @return 是否成功
     */
    override fun updateProductCategory(updateReqVO: ProductCategorySaveReqVO): Boolean {
        validateProductCategoryExists(updateReqVO.id)

        validateParentProductCategoryExists(updateReqVO.parentId)

        validateProductCategoryNameUnique(updateReqVO.id, updateReqVO.name!!)

        val productCategory = convert(updateReqVO, ProductCategory::class.java)

        return updateById(productCategory)
    }

    /**
     * 删除产品分类
     *
     * @param id 产品分类编号
     * @return 是否成功
     */
    override fun deleteProductCategory(id: Long): Boolean {
        val productCategory = validateProductCategoryExists(id)

        validateProductCategoryNotExistsChildren(id)

        publishEvent(productCategory)

        return removeById(id)
    }

    /**
     * 获得产品分类树形列表
     *
     * @return 产品分类树列表
     */
    override fun getProductCategoryTreeList(): List<ProductCategoryTreeRespVO> {
        val categories = mapper.selectListByOrderBy {
            +ProductCategory::sort
        }
        val categoryMap = categories.groupBy { it.parentId }
        categories.forEach {
            it.children = categoryMap[it.id]
        }
        return convert(categoryMap[null] ?: emptyList(), ProductCategoryTreeRespVO::class.java)
    }

    /**
     * 校验产品分类是否存在子级
     *
     * @param id 产品分类编号
     */
    private fun validateProductCategoryNotExistsChildren(id: Long) {
        if (mapper.selectCountByCondition(ProductCategory::parentId eq id) > 0) {
            throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_CHILDREN_EXISTS)
        }
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
    private fun validateProductCategoryExists(id: Long?): ProductCategory =
        id?.let {
            return getById(it) ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)
        } ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)


    /**
     * 校验父级产品分类是否存在
     *
     * @param parentId 父分类编号
     */
    private fun validateParentProductCategoryExists(parentId: Long?) =
        parentId?.let {
            getById(parentId) ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_PARENT_NOT_EXISTS)
        }

}
