package com.hzwl.iot.module.device.controller.product

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.product.vo.product.ProductPageReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductRespVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSimpleRespVO
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.hzwl.iot.module.device.service.product.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
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
    @Parameter(description = "产品编号", name = "id", required = true, example = "1024")
    fun deleteProduct(@PathVariable("id") id: Long): R<Boolean> =
        ok(productService.deleteProduct(id))


    @GetMapping("page")
    @Operation(summary = "分页查询产品列表")
    fun getProductPage(pageReqVO: ProductPageReqVO): R<PageResult<ProductRespVO>> =
        ok(productService.getProductPage(pageReqVO))


    @GetMapping("{id}")
    @Operation(summary = "查询产品信息详情")
    @Parameter(description = "产品编号", name = "id", required = true, example = "1024")
    fun getProduct(@PathVariable("id") id: Long): R<ProductRespVO> {
        val product = productService.getById(id) ?: throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS)
        return ok(convert(product, ProductRespVO::class.java))
    }

    @GetMapping("list-all-simple")
    @Operation(summary = "获取产品精简信息列表", description = "只包含已发布的产品")
    fun getSimpleProductList(): R<List<ProductSimpleRespVO>> =
        ok(productService.getSimpleProductList())

    @PutMapping("{id}/publish")
    @Operation(summary = "发布产品")
    @Parameter(description = "产品编号", name = "id", required = true, example = "1024")
    fun publishProduct(@PathVariable("id") id: Long): R<Boolean> = ok(productService.publishProduct(id))


    @PutMapping("{id}/unpublish")
    @Operation(summary = "取消发布产品")
    @Parameter(description = "产品编号", name = "id", required = true, example = "1024")
    fun unpublishProduct(@PathVariable("id") id: Long): R<Boolean> = ok(productService.unpublishProduct(id))


    @PutMapping("{id}/config")
    @Operation(summary = "修改产品配置")
    @Parameter(description = "产品编号", name = "id", required = true, example = "1024")
    fun updateProductConfig(
        @PathVariable("id") id: Long,
        @RequestBody
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = [Content(
                schema = Schema(type = "object"),
                examples = [ExampleObject(value = "{\"key\":\"value\"}")]
            )],
        )
        config: Map<String, Any>
    ): R<Boolean> =
        ok(productService.updateProductConfig(id, config))

}
