package com.hzwl.iot.framework.mybatis.core.entity

import com.mybatisflex.annotation.Column
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 基础实体对象
 *
 * @author lin
 */
open class BaseEntity(
    @Column(comment = "创建者")
    var creator: String? = null,

    @Column(comment = "创建时间", onInsertValue = "now()")
    val createTime: LocalDateTime? = null,

    @Column(comment = "更新者")
    var updater: String? = null,

    @Column(comment = "更新时间", onInsertValue = "now()", onUpdateValue = "now()")
    val updateTime: LocalDateTime? = null,

    @Column(comment = "是否删除")
    val deleted: Boolean? = null,

    @Column(comment = "删除时间")
    val deletedTime: LocalDateTime? = null,
) : Serializable
