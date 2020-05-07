package com.wu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wu.mapper")
public class EasypoidemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoidemoApplication.class, args);
    }

}
