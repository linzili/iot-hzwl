package com.hzwl.iot.module.device.dal.mapper.product

import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductCategoryMapper:BaseMapper<ProductCategory>
