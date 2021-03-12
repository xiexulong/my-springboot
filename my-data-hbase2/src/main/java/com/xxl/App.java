package com.xxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 * 这个model 未测试
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        SpringApplication.run(App.class, args);
    }
}
