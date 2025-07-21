package com.example.wwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@MapperScan("com.example.wwh.Mapper")
//@ComponentScan("com.example.wwh.Config")
@SpringBootApplication(scanBasePackages = {
        "com.example.wwh",
        "com.example.wwh.service", // 明确扫描服务包
        "com.example.wwh.Config"
})
public class WwhApplication {

    public static void main(String[] args) {

        SpringApplication.run(WwhApplication.class, args);

    }

}

