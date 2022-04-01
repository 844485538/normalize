## 验证码 （kaptcha验证码组件）

验证码作用：
1. 防止广告机注册和发帖、评论。
2. 防止暴力破解密码，特别是有管理员权限的密码。

kaptcha是可配置的，可以生成各种样式的验证码。


## kaptcha
而 kaptcha工作的原理，是调用 com.google.code.kaptcha.servlet.KaptchaServlet，生成一个图片。同时将生成的验证码字符串放到 HttpSession中，直接从session中获取这张验证码图片，而不会占用实际内存。

![](https://img-blog.csdnimg.cn/7f5f9f7eba7d4fdc9a6f98c9f3cbad29.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5p2O5oCd5YeA,size_20,color_FFFFFF,t_70,g_se,x_16)


## 使用步骤

1. POM引入依赖
```pom
<dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.2</version>
</dependency>
```

2. 创建工具类 KaprchaUtil
3. 通过调用调用工具类生称验证码


## 示例

获取验证码
GET - localhost:8080/auth/code/init

验证验证码
GET - localhost:8080/auth/code/check?authCode=4017