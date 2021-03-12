package com.xxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.xxl.dao")
public class App 
{
    /**
     * 该model 中包含 mybatis 的使用以及 freemarker的使用
     * @param args
     */
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
