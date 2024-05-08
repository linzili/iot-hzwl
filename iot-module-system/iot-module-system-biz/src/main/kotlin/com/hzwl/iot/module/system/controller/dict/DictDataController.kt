package com.hzwl.iot.module.system.controller.dict

import com.hzwl.iot.common.exception.ServiceException
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataRespVO
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.hzwl.iot.module.system.service.dict.DictDataService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

/**
 * 字典数据控制器
 * @author lin
 */
@RestController
@RequestMapping("system/dict-data")
@Tag(name = "管理后台 - 字典数据")
class DictDataController(
    val dictDataService: DictDataService,
) {

    @PostMapping
    @Operation(summary = "新增字典数据")
    @ApiResponse(description = "字典数据编号")
    fun create(@RequestBody createReqVo: DictDataSaveReqVo): R<Long> {
        val dictDataId = dictDataService.createDictData(createReqVo)
        return ok(dictDataId)
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询字典数据详情")
    @Parameter(name = "id", description = "字典数据编号", required = true, example = "1024")
    fun getDictData(@PathVariable("id") id: Long): R<DictDataRespVO> {
        val dictData = dictDataService.getById(id)
            ?: throw ServiceException(ErrorCodeConstants.DICT_DATA_NOT_EXISTS)
        return ok(convert.convert(dictData, DictDataRespVO::class.java))
    }

}
