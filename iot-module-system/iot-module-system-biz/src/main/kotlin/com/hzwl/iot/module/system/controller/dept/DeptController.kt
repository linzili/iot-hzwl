package com.hzwl.iot.module.system.controller.dept

import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.common.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.dept.vo.DeptListReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptRespVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSaveReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSimpleRespVO
import com.hzwl.iot.module.system.service.dept.DeptService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/dept")
@Tag(name = "管理后台 - 部门管理")
class DeptController(
    private val deptService: DeptService
) {
    @PostMapping
    @Operation(summary = "创建部门")
    fun createDept(@RequestBody createReqVo: DeptSaveReqVO): R<Long> = ok(deptService.createDept(createReqVo))

    @PutMapping
    @Operation(summary = "修改部门")
    fun updateDept(@RequestBody updateReqVo: DeptSaveReqVO): R<Boolean> =
        ok(deptService.updateDept(updateReqVo))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    @Parameter(description = "部门编号", name = "id", required = true, example = "1024")
    fun deleteDept(@PathVariable("id") id: Long): R<Boolean> = ok(deptService.deleteDept(id))

    @GetMapping("list")
    @Operation(summary = "获取部门列表")
    fun getDeptList(reqVO: DeptListReqVO): R<List<DeptRespVO>> =
        ok(deptService.getDeptList(reqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询部门详情")
    @Parameter(description = "部门编号", name = "id", required = true, example = "1024")
    fun getDept(@PathVariable("id") id: Long): R<DeptRespVO> =
        ok(convert(deptService.getDeptById(id), DeptRespVO::class.java))

    @GetMapping("list-all-simple")
    @Operation(summary = "获取部门精简信息列表", description = "只包含被开启的部门，主要用于前端的下拉选项")
    fun listSimpleDept(): R<List<DeptSimpleRespVO>> = ok(deptService.getSimpleDeptList())


    @GetMapping("list-tree")
    @Operation(summary = "获取部门树列表")
    fun listDeptTree(reqVO: DeptListReqVO): R<List<DeptRespVO>> = ok(deptService.getDeptTreeList(reqVO))

    @GetMapping("list-all-simple-tree")
    @Operation(summary = "获取部门树精简信息列表", description = "只包含被开启的部门，主要用于前端的下拉选项")
    fun listSimpleDeptTree(): R<List<DeptSimpleRespVO>> = ok(deptService.getSimpleDeptTreeList())
}
