package com.hzwl.iot.module.device.controller.device

import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.device.vo.DeviceSaveReqVO
import com.hzwl.iot.module.device.service.device.DeviceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

/**
 * 设备控制器
 * @author lin
 */
@RestController
@RequestMapping("device-manager/device")
@Tag(name = "设备管理 - 设备")
class DeviceController(
    val deviceService: DeviceService
) {
    @PostMapping
    @Operation(summary = "创建设备")
    fun createDevice(createReqVO: DeviceSaveReqVO): R<String> =
       ok( deviceService.createDevice(createReqVO))

    @PutMapping
    @Operation(summary = "修改设备")
    fun updateDevice(updateReqVO: DeviceSaveReqVO): R<Boolean> =
        ok(deviceService.updateDevice(updateReqVO))


    @DeleteMapping("{id}")
    @Operation(summary = "删除设备")
    @Parameter(description = "设备编号", name = "id", required = true)
    fun deleteDevice(@PathVariable("id") id: String): R<Boolean> =
        ok(deviceService.deleteDevice(id))
}
