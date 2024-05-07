package com.hzwl.framework.mybatis.core.entity

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
    var createTime: LocalDateTime? = null,

    @Column(comment = "更新者")
    var updater: String? = null,

    @Column(comment = "更新时间", onUpdateValue = "now()")
    var updateTime: LocalDateTime? = null,

    @Column(comment = "是否删除")
    var deleted: Boolean = false,

    @Column(comment = "删除时间")
    var deleteTime: LocalDateTime? = null,
) : Serializable
