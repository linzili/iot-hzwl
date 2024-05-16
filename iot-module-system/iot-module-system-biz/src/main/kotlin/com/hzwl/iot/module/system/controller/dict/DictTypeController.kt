package com.hzwl.iot.module.system.controller.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypePageReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeRespVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSimpleRespVO
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.hzwl.iot.module.system.service.dict.DictTypeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

/**
 * 字典类型控制器
 * @author lin
 */
@RestController
@RequestMapping("system/dict-type")
@Tag(name = "管理后台 - 字典类型")
class DictTypeController(
    val dictTypeService: DictTypeService
) {
    @PostMapping
    @Operation(summary = "新增字典类型")
    fun createDictType(@RequestBody createReqVo: DictTypeSaveReqVO): R<Long> =
        ok(dictTypeService.createDictType(createReqVo))

    @PutMapping
    @Operation(summary = "修改字典类型")
    fun updateDictType(@RequestBody updateReqVo: DictTypeSaveReqVO): R<Boolean> =
        ok(dictTypeService.updateDictType(updateReqVo))

    @DeleteMapping("{id}")
    @Operation(summary = "删除字典类型")
    @Parameter(description = "字典类型编号", name = "id", required = true, example = "1024")
    fun deleteDictType(@PathVariable("id") id: Long): R<Boolean> =
        ok(dictTypeService.deleteDictType(id))

    @GetMapping("page")
    @Operation(summary = "分页查询字典类型")
    fun getDictTypePage(pageReqVO: DictTypePageReqVO): R<PageResult<DictTypeRespVO>> =
        ok(dictTypeService.getDictTypePage(pageReqVO))

    @GetMapping("{id}")
    @Operation(summary = "查询字典类型详情")
    @Parameter(description = "字典类型编号", name = "id", required = true, example = "1024")
    fun getDictType(@PathVariable("id") id: Long): R<DictTypeRespVO> {
        val dictType = dictTypeService.getById(id) ?: throw exception(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS)
        return ok(convert(dictType, DictTypeRespVO::class.java))
    }

    @GetMapping("list-all-simple")
    @Operation(summary = "获取字典类型精简信息列表", description = "只包含被开启的字典类型, 且模拟一个 tree 结构")
    fun getSimpleDictTypes(): R<List<DictTypeSimpleRespVO>> =
        ok(dictTypeService.getSimpleDictTypeList())
}
