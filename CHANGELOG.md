## [v0.1.6](https://github.com/linzili/iot-hzwl/compare/v0.1.5...v0.1.6)

### features

- 角色管理增删改查
- 部门管理增删改查
- 用户添加部门属性

### fixed

- 修复Long? 类型在返回给前端时，Long?不会被转为string的问题

## [v0.1.5](https://github.com/linzili/iot-hzwl/compare/v0.1.4...v0.1.5)

### features

- 实现TenantUtils工具，用于操作指定/忽略租户操作
- 添加租户过滤器，获取租户信息
- 新增租户逻辑优化：创建租户时创建租户管理员
- 实现TenantContextHolder 用于存储租户上下文信息
- 新增iot-spring-boot-starter-biz-tenant 模块
- 优化新建用户逻辑
- 字典值改为 Int 类型

## [v0.1.4](https://github.com/linzili/iot-hzwl/compare/v0.1.3...v0.1.4)

### features

- 租户管理
    - 租户套餐管理增删改查
    - 租户管理增删改查
- 用户管理
    - 用户增删改查

## [v0.1.3](https://github.com/linzili/iot-hzwl/compare/v0.1.2...v0.1.3)

### features

- 租户管理
    - 租户套餐管理增删改查
    - 租户管理增删改查
- 用户管理
    - 用户增删改查

## [v0.1.2](https://github.com/linzili/iot-hzwl/compare/v0.1.1...v0.1.2)

### features

- 添加redis模块
- 添加CommaSplitTypeHandler处理器，用于存储list到数据库时的转换
- 添加IdWorker工具Bean
- 添加跨域配置
- 添加web参数序列化工具，Long - String 互相转换

## [v0.1.1](https://github.com/linzili/iot-hzwl/compare/v0.1.0...v0.1.1)

### Change

- 修改部署域名为hze2.com

## [v0.1.0](https://github.com/linzili/iot-hzwl/releases/tag/v0.1.0)

### Features

- 网络调试助手
    - 支持 Tcp/Udp 协议设备网络调试
    - 支持 16进制 数据发送
    - 动态端口
    - 断线检测
- 系统管理
    - 字典数据管理
    - 字典类型管理
- 设备管理
    - 产品类型管理
    - 产品管理
    - 设备管理

