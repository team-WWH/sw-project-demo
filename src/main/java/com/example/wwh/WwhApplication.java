package com.example.wwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.wwh.Mapper")
@ComponentScan("com.example.wwh.Config")
public class WwhApplication {

    public static void main(String[] args) {

        SpringApplication.run(WwhApplication.class, args);

    }

}

