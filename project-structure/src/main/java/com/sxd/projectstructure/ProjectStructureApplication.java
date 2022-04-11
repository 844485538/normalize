package com.sxd.projectstructure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 李健新
 *
 *    框架模板示例工程
 */
@MapperScan(basePackages = "com.sxd.projectstructure.mapper")
@SpringBootApplication
public class ProjectStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectStructureApplication.class, args);
    }

}
