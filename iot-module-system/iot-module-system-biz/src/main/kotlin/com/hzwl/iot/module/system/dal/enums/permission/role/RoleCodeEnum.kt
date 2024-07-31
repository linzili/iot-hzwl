enum class RoleCodeEnum(
    val code: String,
    val roleName: String
) {
    SUPER_ADMIN("super_admin", "超级管理员"),
    TENANT_ADMIN("tenant_admin", "租户管理员"),
    ;
    companion object {
        fun isSuperAdmin(code: String): Boolean = SUPER_ADMIN.code == code
    }
}
