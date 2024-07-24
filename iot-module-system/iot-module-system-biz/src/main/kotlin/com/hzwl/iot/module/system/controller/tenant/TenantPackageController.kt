package com.hzwl.iot.module.system.controller.tenant

import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackagePageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSimpleRespVO
import com.hzwl.iot.module.system.service.tenant.TenantPackageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/tenant-package")
@Tag(name = "管理后台 - 租户套餐管理")
class TenantPackageController(
    private val tenantPackageService: TenantPackageService
) {
    @PostMapping
    @Operation(summary = "创建租户套餐")
    fun createTenantPackage(@RequestBody createReqVo: TenantPackageSaveReqVO): R<Long> =
        ok(tenantPackageService.createTenantPackage(createReqVo))


    @PutMapping
    @Operation(summary = "修改租户套餐")
    fun updateTenantPackage(@RequestBody updateReqVo: TenantPackageSaveReqVO): R<Boolean> =
        ok(tenantPackageService.updateTenantPackage(updateReqVo))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户套餐")
    @Parameter(description = "租户套餐编号", name = "id", required = true, example = "1024")
    fun deleteTenantPackage(@PathVariable("id") id: Long): R<Boolean> = ok(tenantPackageService.deleteTenantPackage(id))


    @GetMapping("page")
    @Operation(summary = "分页查询租户套餐")
    fun getTenantPackagePage(pageReqVO: TenantPackagePageReqVO): R<PageResult<TenantPackageRespVO>> =
        ok(tenantPackageService.getTenantPackagePage(pageReqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询租户套餐详情")
    @Parameter(description = "租户套餐编号", name = "id", required = true, example = "1024")
    fun getTenantPackage(@PathVariable("id") id: Long): R<TenantPackageRespVO> =
        ok(convert(tenantPackageService.getTenantPackageById(id), TenantPackageRespVO::class.java))

    @GetMapping("list-all-simple")
    @Operation(summary = "获取租户套餐精简信息列表", description = "只包含被开启的租户套餐，主要用于前端的下拉选项")
    fun listSimpleTenantPackage(): R<List<TenantPackageSimpleRespVO>> =
        ok(tenantPackageService.getSimpleTenantPackageList())
}
