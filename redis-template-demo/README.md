## RedisTemplate 使用方法

1. 引入Maven依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.45</version>
        </dependency>
```
2. 添加RedisConfig 配置类、FastJSON配置类
   - JDKSerializer的序列化方式效率低，这里使用 FastJsonRedisSerializer 来替代，所以需要引入fastjson
3. 添加RedisUtils操作工具类
