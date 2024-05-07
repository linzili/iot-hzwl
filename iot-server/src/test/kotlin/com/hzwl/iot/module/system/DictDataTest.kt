package com.hzwl.iot.module.system

import com.hzwl.ServerApplication
import com.hzwl.iot.module.system.dal.entity.DictData
import com.hzwl.iot.module.system.dal.mapper.DictDataMapper
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


/**
 * TODO
 * @author lin
 */
@SpringBootTest(classes = [ServerApplication::class])
class DictDataTest {


    @Resource
    lateinit var dictDataMapper: DictDataMapper

    @Test
    fun insert() {
        dictDataMapper.insert(DictData(1, 1, "label", "value", "dictType", 1, "colorType", "cssClass", "remark"))
    }

    @Test
    fun select() {
        println(dictDataMapper.selectOneById(1540).createTime)
    }


    @Test
    fun delete() {
        dictDataMapper.deleteById(1540)
    }
}
