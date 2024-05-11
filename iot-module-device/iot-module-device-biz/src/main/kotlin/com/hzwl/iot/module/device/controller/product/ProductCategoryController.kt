package com.hzwl.iot.module.device.controller.product

import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.product.vo.category.ProductCategorySaveReqVO
import com.hzwl.iot.module.device.service.product.ProductCategoryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


/**
 * 产品分类控制器
 * @author lin
 */
@RestController
@RequestMapping("device/product-category")
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
}
