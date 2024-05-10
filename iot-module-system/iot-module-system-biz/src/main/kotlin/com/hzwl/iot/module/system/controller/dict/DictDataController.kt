package com.hzwl.iot.module.system.controller.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.web.pojo.R
import com.hzwl.iot.framework.web.pojo.R.Companion.ok
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataPageReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataRespVO
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSimpleRespVO
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.hzwl.iot.module.system.service.dict.DictDataService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
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
    fun createDictData(@RequestBody createReqVo: DictDataSaveReqVo): R<Long> =
        ok(dictDataService.createDictData(createReqVo))


    @PutMapping
    @Operation(summary = "修改字典数据")
    fun updateDictData(@RequestBody updateReqVo: DictDataSaveReqVo): R<Boolean> =
        ok(dictDataService.updateDictData(updateReqVo))

    @DeleteMapping("{id}")
    @Operation(summary = "删除字典数据")
    @Parameter(name = "id", description = "字典数据编号", required = true, example = "1024")
    fun deleteDictData(@PathVariable("id") id: Long): R<Boolean> =
        ok(dictDataService.deleteDictData(id))


    @GetMapping("list-all-simple")
    @Operation(summary = "获取全部字典数据列表", description = "一般用于管理后台缓存字典数据在本地")
    fun getSimpleDictDataList(): R<List<DictDataSimpleRespVO>> =
        ok(dictDataService.getSimpleDictDataList())

    @GetMapping("page")
    @Operation(summary = "获得指定字典类型的分页列表")
    fun getDictDataPageByType(pageReqVo: DictDataPageReqVo): R<PageResult<DictDataRespVO>> =
        ok(dictDataService.getDictDataPage(pageReqVo))


    @GetMapping("{id}")
    @Operation(summary = "查询字典数据详情")
    @Parameter(name = "id", description = "字典数据编号", required = true, example = "1024")
    fun getDictData(@PathVariable("id") id: Long): R<DictDataRespVO> {
        val dictData = dictDataService.getById(id)
            ?: throw exception(ErrorCodeConstants.DICT_DATA_NOT_EXISTS)
        return ok(convert(dictData, DictDataRespVO::class.java))
    }

}
