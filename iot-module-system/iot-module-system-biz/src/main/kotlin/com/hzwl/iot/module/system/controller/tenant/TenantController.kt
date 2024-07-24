package com.hzwl.iot.module.system.controller.tenant

import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantPageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSimpleRespVO
import com.hzwl.iot.module.system.service.tenant.TenantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/tenant")
@Tag(name = "管理后台 - 租户管理")
class TenantController(
    private val tenantService: TenantService
) {

    @GetMapping("get-id-by-name")
    @Operation(summary = "根据租户名查询租户id")
    @Parameter(description = "租户名", name = "name", required = true, example = "翰臻云")
    fun getTenantIdByName(@RequestParam("name") name: String): R<Long> {
        val tenant = tenantService.getTenantByName(name)
        return ok(tenant.id!!)
    }

    @GetMapping("get-by-website")
    @Operation(summary = "根据租户域名查询租户信息")
    @Parameter(description = "租户域名", name = "website", required = true, example = "hanzhenyun.com")
    fun getTenantIdByWebsite(@RequestParam("website") website: String): R<TenantSimpleRespVO> {
        val tenant = tenantService.getTenantByWebsite(website)
        return ok(convert(tenant, TenantSimpleRespVO::class.java))
    }

    @PostMapping
    @Operation(summary = "创建租户")
    fun createTenant(@RequestBody createReqVo: TenantSaveReqVO): R<Long> = ok(tenantService.createTenant(createReqVo))


    @PutMapping
    @Operation(summary = "修改租户")
    fun updateTenant(@RequestBody updateReqVo: TenantSaveReqVO): R<Boolean> =
        ok(tenantService.updateTenant(updateReqVo))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户")
    @Parameter(description = "租户编号", name = "id", required = true, example = "1024")
    fun deleteTenant(@PathVariable("id") id: Long): R<Boolean> = ok(tenantService.deleteTenant(id))


    @GetMapping("page")
    @Operation(summary = "分页查询租户")
    fun getTenantPage(pageReqVO: TenantPageReqVO): R<PageResult<TenantRespVO>> =
        ok(tenantService.getTenantPage(pageReqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询租户详情")
    @Parameter(description = "租户编号", name = "id", required = true, example = "1024")
    fun getTenant(@PathVariable("id") id: Long): R<TenantRespVO> =
        ok(convert(tenantService.getTenantById(id), TenantRespVO::class.java))

    @GetMapping("list-all-simple")
    @Operation(summary = "获取租户精简信息列表", description = "只包含被开启的租户，主要用于前端的下拉选项")
    fun listSimpleTenant(): R<List<TenantSimpleRespVO>> = ok(tenantService.getSimpleTenantList())
}
