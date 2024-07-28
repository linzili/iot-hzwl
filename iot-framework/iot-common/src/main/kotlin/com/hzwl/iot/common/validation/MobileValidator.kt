package com.hzwl.iot.common.validation

import cn.hutool.core.util.PhoneUtil
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.apache.commons.lang3.StringUtils

class MobileValidator : ConstraintValidator<Mobile, String> {
    /**
     * Implements the validation logic.
     * The state of `value` must not be altered.
     *
     *
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return `false` if `value` does not pass the constraint
     */
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean =
        if (StringUtils.isEmpty(value))
            true
        else
            PhoneUtil.isPhone(value)
}
