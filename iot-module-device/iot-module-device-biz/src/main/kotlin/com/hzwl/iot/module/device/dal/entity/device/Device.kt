package com.hzwl.iot.module.device.dal.entity.device

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators


@Table("device_device")
data class Device(

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: String? = null,

    val name: String,

    val photoUrl: String?,

    val productId: Long,

    var productName: String,

    val status: CommonStatusEnum,

    val description: String?,

    val remark: String?,

    ) : TenantEntity() {


}
