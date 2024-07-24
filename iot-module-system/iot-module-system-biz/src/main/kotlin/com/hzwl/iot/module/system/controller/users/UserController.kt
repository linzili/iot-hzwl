package com.hzwl.iot.module.system.controller.users

import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.users.vo.UserPageReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserRespVO

import com.hzwl.iot.module.system.controller.users.vo.UserSaveReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserSimpleRespVO
import com.hzwl.iot.module.system.service.users.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/users")
@Tag(name = "管理后台 - 用户管理")
class UserController (
    private val userService: UserService
){

    @PostMapping
    @Operation(summary = "创建用户")
    fun createUser(@RequestBody createReqVo: UserSaveReqVO): R<Long> = ok(userService.createUser(createReqVo))


    @PutMapping
    @Operation(summary = "修改用户")
    fun updateUser(@RequestBody updateReqVo: UserSaveReqVO): R<Boolean> =
        ok(userService.updateUser(updateReqVo))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    @Parameter(description = "用户编号", name = "id", required = true, example = "1024")
    fun deleteUser(@PathVariable("id") id: Long): R<Boolean> = ok(userService.deleteUser(id))


    @GetMapping("page")
    @Operation(summary = "分页查询用户")
    fun getUserPage(pageReqVO: UserPageReqVO): R<PageResult<UserRespVO>> =
        ok(userService.getUserPage(pageReqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询用户详情")
    @Parameter(description = "用户编号", name = "id", required = true, example = "1024")
    fun getUser(@PathVariable("id") id: Long): R<UserRespVO> =
        ok(convert(userService.getUserById(id), UserRespVO::class.java))

    @GetMapping("list-all-simple")
    @Operation(summary = "获取用户精简信息列表", description = "只包含被开启的用户，主要用于前端的下拉选项")
    fun listSimpleUser(): R<List<UserSimpleRespVO>> = ok(userService.getSimpleUserList())
}
