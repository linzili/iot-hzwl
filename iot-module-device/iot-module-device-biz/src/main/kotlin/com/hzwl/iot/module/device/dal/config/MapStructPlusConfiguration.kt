package com.hzwl.iot.module.device.dal.config

import io.github.linpeilie.annotations.MapperConfig

@MapperConfig(
    adapterClassName = "DeviceConvertMapperAdapter",
    adapterPackage = "com.hzwl.iot.module.device",
    mapAdapterClassName = "DeviceMapConvertMapperAdapter"
)
interface MapStructPlusConfiguration
