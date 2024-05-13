package com.hzwl.iot.product

import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.service.product.ProductCategoryService
import io.github.linpeilie.ConverterMapperAdapter
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

    @Resource
    lateinit var converterMapperAdapter: ConverterMapperAdapter

    @Test
    fun converterMapperAdapter() {
        val list =
            converterMapperAdapter.chimddep_ProductCategoryToProductCategoryTreeRespVO(
                listOf(
                    ProductCategory(
                        id = 1L,
                        name = "1",
                        sort = 1,
                        parentId = 0L,
                        remark = "123",
                        tenantId = 1L,
                        children = listOf(
                            ProductCategory(
                                id = 2L,
                                name = "2",
                                sort = 1,
                                parentId = 1L,
                                remark = "123",
                                tenantId = 1L,
                                children = listOf()
                            )
                        )
                    )
                )
            )
        list
    }
}
