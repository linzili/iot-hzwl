package com.hzwl.iot.module.device.dal.mapper.product

import com.hzwl.iot.module.device.dal.entity.product.Product
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper :BaseMapper<Product>
