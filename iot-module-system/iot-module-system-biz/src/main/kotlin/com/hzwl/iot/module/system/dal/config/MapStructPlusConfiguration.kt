package com.hzwl.iot.module.device.dal.config

import io.github.linpeilie.annotations.MapperConfig

@MapperConfig(
    adapterClassName = "SystemConvertMapperAdapter",
    adapterPackage = "com.hzwl.iot.module.device",
    mapAdapterClassName = "SystemMapConvertMapperAdapter"
)
interface MapStructPlusConfiguration
