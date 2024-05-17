package com.hzwl.iot.module.device.controller.device

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.device.controller.device.vo.DevicePageReqVO
import com.hzwl.iot.module.device.controller.device.vo.DeviceRespVO
import com.hzwl.iot.module.device.controller.device.vo.DeviceSaveReqVO
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
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
    fun createDevice(@RequestBody createReqVO: DeviceSaveReqVO): R<String> =
        ok(deviceService.createDevice(createReqVO))

    @PutMapping
    @Operation(summary = "修改设备")
    fun updateDevice(@RequestBody updateReqVO: DeviceSaveReqVO): R<Boolean> =
        ok(deviceService.updateDevice(updateReqVO))

    @DeleteMapping("{id}")
    @Operation(summary = "删除设备")
    @Parameter(description = "设备编号", name = "id", required = true, example = "1024")
    fun deleteDevice(@PathVariable("id") id: String): R<Boolean> =
        ok(deviceService.deleteDevice(id))

    @GetMapping("{id}")
    @Operation(summary = "查询设备详情")
    @Parameter(description = "设备编号", name = "id", required = true, example = "1024")
    fun getDevice(@PathVariable("id") id: String): R<DeviceRespVO> {
        val device = deviceService.getById(id) ?: throw exception(ErrorCodeConstants.DEVICE_NOT_EXISTS)
        return ok(convert(device, DeviceRespVO::class.java))
    }

    @GetMapping("page")
    @Operation(summary = "分页查询设备列表")
    fun getDevicePage(pageReqVO: DevicePageReqVO): R<PageResult<DeviceRespVO>> =
        ok(deviceService.getDevicePage(pageReqVO))


}
