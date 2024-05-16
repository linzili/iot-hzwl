package com.hzwl.iot.module.device.controller.product

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategoryRespVO
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategorySaveReqVO
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategoryTreeRespVO
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.hzwl.iot.module.device.service.product.ProductCategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


/**
 * 产品分类控制器
 * @author lin
 */
@RestController
@RequestMapping("device-manager/product-category")
@Tag(name = "设备管理 - 产品分类")
class ProductCategoryController(
    val productCategoryService: ProductCategoryService
) {
    @PostMapping
    @Operation(summary = "创建产品分类")
    fun createProductCategory(@RequestBody createReqVO: ProductCategorySaveReqVO): R<Long> =
        ok(productCategoryService.createProductCategory(createReqVO))

    @PutMapping
    @Operation(summary = "修改产品分类")
    fun updateProductCategory(@RequestBody updateReqVO: ProductCategorySaveReqVO): R<Boolean> =
        ok(productCategoryService.updateProductCategory(updateReqVO))

    @DeleteMapping("{id}")
    @Operation(summary = "删除产品分类")
    fun deleteProductCategory(@PathVariable("id") id: Long): R<Boolean> =
        ok(productCategoryService.deleteProductCategory(id))

    @GetMapping("{id}")
    @Operation(summary = "查询产品分类详情")
    fun getProductCategory(@PathVariable("id") id: Long): R<ProductCategoryRespVO> {
        val productCategory = productCategoryService.getById(id)
            ?: throw exception(ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS)
        return ok(convert(productCategory, ProductCategoryRespVO::class.java))
    }

    @GetMapping("tree-list")
    @Operation(summary = "获取产品分类树形列表")
    fun getProductCategoryTreeList(): R<List<ProductCategoryTreeRespVO>> =
        ok(productCategoryService.getProductCategoryTreeList())

}
