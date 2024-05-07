package com.hzwl.iot.module.system.controller.dict

import com.hzwl.framework.common.enums.CommonStatusEnum
import com.hzwl.framework.web.pojo.R
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.service.dict.DictDataService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * 字典数据控制器
 * @author lin
 */
@RestController
@RequestMapping("system/dict-data")
@Tag(name = "字典数据")
class DictDataController(
    val dictDataService: DictDataService,
) {

    @PostMapping
    @Operation(summary = "保存字典数据")
    fun save(): R<DictData> {
        return R.ok(DictData(1, 1, "1", "1", "1", CommonStatusEnum.ENABLE, "1", "1", "1"))
    }


}
