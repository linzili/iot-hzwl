package com.hzwl.iot.product

import com.hzwl.iot.module.device.service.product.ProductCategoryService
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductCategoryServiceTest {

    @Resource
    lateinit var productCategoryService: ProductCategoryService


    @Test
    fun tenantTest() {
        productCategoryService.mapper.selectAll()
    }


    @Test
    fun treeListTest() {
        productCategoryService.getProductCategoryTreeList().forEach(::println)
    }

}
