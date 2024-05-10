package com.hzwl.iot.dict

import com.hzwl.iot.module.system.service.dict.DictTypeService
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


/**
 * 测试字典类型
 * @author lin
 */

@SpringBootTest
class DictTypeServiceTest {

    @Resource
    lateinit var dictTypeService: DictTypeService

    @Test
    fun deleteDictTypeTest() {
        dictTypeService.deleteDictType(1024)
    }
}
