package com.hzwl.iot.module.device.service.product

import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.hzwl.iot.module.device.dal.mapper.product.ProductCategoryMapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class ProductCategoryServiceImpl : ServiceImpl<ProductCategoryMapper, ProductCategory>(), ProductCategoryService
