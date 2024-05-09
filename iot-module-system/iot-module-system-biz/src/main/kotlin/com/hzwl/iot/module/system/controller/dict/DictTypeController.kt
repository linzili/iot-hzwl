package com.hzwl.iot.module.system.controller.dict

import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
import com.hzwl.iot.module.system.service.dict.DictTypeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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
}
