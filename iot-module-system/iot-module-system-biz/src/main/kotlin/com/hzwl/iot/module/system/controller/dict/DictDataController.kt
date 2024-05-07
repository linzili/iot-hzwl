package com.hzwl.iot.module.system.controller.dict

import com.hzwl.iot.module.system.service.dict.DictDataService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * 字典数据控制器
 * @author lin
 */
@RestController
@RequestMapping("system/dict-data")
class DictDataController(
    val dictDataService: DictDataService,
) {

}
