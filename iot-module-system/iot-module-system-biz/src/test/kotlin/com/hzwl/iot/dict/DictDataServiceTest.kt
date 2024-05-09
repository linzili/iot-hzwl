package com.hzwl.iot.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataPageReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.dal.mapper.dict.DictDataMapper
import com.hzwl.iot.module.system.service.dict.DictDataService
import com.mybatisflex.kotlin.extensions.db.mapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
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


    @Test
    fun listSimpleDictDataList() {
        val dictDataMapper: DictDataMapper = mapper()
        dictDataMapper.selectSimpleDictDataList().forEach(::println)
    }

    @Test
    fun testUpdate() {
        val dictDataMapper: DictDataMapper = mapper()

        val dictData = DictData(value = "dajio", label = "djwoaidji", sort = 1, dictType = "sys_user_sex", id = null)
        dictDataMapper.updateByCondition(dictData, (DictData::id eq 1543))
    }


    @Test
    fun testPage() {
        val dictDataMapper: DictDataMapper = mapper()
        val dictDataPageReqVo = DictDataPageReqVo("null", "sys", null)
        dictDataPageReqVo.page = 1
        dictDataPageReqVo.size = 10
        dictDataMapper.selectPage(dictDataPageReqVo)
    }
}
