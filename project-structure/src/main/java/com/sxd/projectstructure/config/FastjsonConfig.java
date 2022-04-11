package com.sxd.projectstructure.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.nio.charset.StandardCharsets;

/**
 * @Author 李健新
 * @Date 2020/11/4
 * @Description
 *
 *      FastJSON 配置类
 */
@Configuration
public class FastjsonConfig {
	
	@Bean
	FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		// 解析设置
		config.setDateFormat("yyyy-MM-dd HH:mm:ss");
		config.setCharset(StandardCharsets.UTF_8);
		config.setSerializerFeatures(
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.PrettyFormat
				);
		converter.setFastJsonConfig(config);
		return converter;
	}

}