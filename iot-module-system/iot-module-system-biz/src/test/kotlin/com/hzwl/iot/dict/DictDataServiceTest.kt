package com.hzwl.iot.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.service.dict.DictDataService
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

/**
 * 测试字典数据
 * @author lin
 */
@SpringBootTest
class DictDataServiceTest {

    @Resource
    lateinit var dictDataService: DictDataService

    @Test
    fun create() {
        dictDataService.createDictData(
            DictDataSaveReqVo(
                null,
                1,
                "男",
                "男",
                "sys_user_sex",
                CommonStatusEnum.ENABLE
            )
        )
    }
}
