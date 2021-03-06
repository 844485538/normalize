## 规范说明

为了降低协同成本，提升沟通效率特此提出此规范。

[具体规范内容请跳转 Wiki 查看](https://github.com/844485538/normalize/wiki)

## 重点

1. 项目目录结构及目录名称必须符合此规范。
2. 类所在目录必须符合此规范。
3. 接口返回值必须使用指定的 ResponseTemplate.java 类
4. 代码注释说明必须符合规范。

## project-structure 模板项目

### 必须包含的框架

- Spring Boot
- Spring Boot Web
- fastjson
- druid
- mybatis-plus
- JSR330
  - 参数验证框架

### 模板项目转换为具体项目步骤

1. 删除示例的 Controller、Service、Mapper
2. 修改 Pom 中的 artifactId、name、description
3. 修改根目录名称
4. 修改配置文件
   - port
   - context-path
   - 数据库连接
5. 修改启动类
   - 修改启动类名称
   - 修改@MapperScan 指定的路径

## 示例工程（可 copy 直接使用）

- utils 简单功能工具类
  - MagicPackageUtils WOL 工具类
  - StringUtils 字符串工具类
  - TimeUtils 时间工具类
  - UUIDUtils UUID 工具类
- auth-code 页面验证码操作工具类示例
