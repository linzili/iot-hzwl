package com.hzwl.iot.module.device.controller.product

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.hzwl.iot.module.device.service.product.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

/**
 * 产品控制器
 * @author lin
 */
@RestController
@RequestMapping("device/product")
@Tag(name = "设备管理 - 产品")
class ProductController(
    val productService: ProductService
) {

    @PostMapping
    @Operation(summary = "创建产品")
    fun createProduct(@RequestBody createReqVo: ProductSaveReqVO): R<Long> =
        ok(productService.createProduct(createReqVo))

    @PutMapping
    @Operation(summary = "修改产品")
    fun updateProduct(@RequestBody updateReqVo: ProductSaveReqVO): R<Boolean> =
        ok(productService.updateProduct(updateReqVo))

    @DeleteMapping("{id}")
    @Operation(summary = "删除产品")
    fun deleteProduct(@PathVariable("id") id: Long): R<Boolean> {
        return ok(productService.deleteProduct(id))
    }

    @GetMapping("page")
    @Operation(summary = "分页查询产品列表")
    fun getProductPage(pageReqVO: ProductPageReqVO): R<PageResult<ProductRespVO>> =
        ok(productService.getProductPage(pageReqVO))


    @GetMapping("{id}")
    @Operation(summary = "查询产品信息详情")
    fun getProduct(@PathVariable("id") id: Long): R<ProductRespVO> {
        val product = productService.getById(id) ?: throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)
        return ok(convert(product, ProductRespVO::class.java))
    }


}
