package com.hzwl.iot.module.system.controller.permission

import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.permission.vo.role.RolePageReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleRespVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSaveReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSimpleRespVO
import com.hzwl.iot.module.system.service.permission.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/role")
@Tag(name = "管理后台 - 角色管理")
class RoleController ( private val roleService: RoleService
){

    @PostMapping
    @Operation(summary = "创建角色")
    fun createRole(@RequestBody createReqVo: RoleSaveReqVO): R<Long> = ok(roleService.createRole(createReqVo))


    @PutMapping
    @Operation(summary = "修改角色")
    fun updateRole(@RequestBody updateReqVo: RoleSaveReqVO): R<Boolean> =
        ok(roleService.updateRole(updateReqVo))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    @Parameter(description = "角色编号", name = "id", required = true, example = "1024")
    fun deleteRole(@PathVariable("id") id: Long): R<Boolean> = ok(roleService.deleteRole(id))

    @GetMapping("page")
    @Operation(summary = "分页查询角色")
    fun getRolePage(pageReqVO: RolePageReqVO): R<PageResult<RoleRespVO>> =
        ok(roleService.getRolePage(pageReqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询角色详情")
    @Parameter(description = "角色编号", name = "id", required = true, example = "1024")
    fun getRole(@PathVariable("id") id: Long): R<RoleRespVO> =
        ok(convert(roleService.getRoleById(id), RoleRespVO::class.java))

    @GetMapping("list-all-simple")
    @Operation(summary = "获取角色精简信息列表", description = "只包含被开启的角色，主要用于前端的下拉选项")
    fun listSimpleRole(): R<List<RoleSimpleRespVO>> = ok(roleService.getSimpleRoleList())
}
