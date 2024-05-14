package com.hzwl.iot.module.device.controller.product

import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.product.vo.product.ProductSaveReqVO
import com.hzwl.iot.module.device.service.product.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun createProduct(@RequestBody createReqVo: ProductSaveReqVO): R<Long> = ok(productService.createProduct(createReqVo))

}
